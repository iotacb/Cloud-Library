package de.iotacb.cloud.core.window;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowFocusCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowIcon;
import static org.lwjgl.glfw.GLFW.glfwSetWindowOpacity;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.DoubleBuffer;
import java.nio.file.Paths;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import de.iotacb.cloud.core.window.icon.ImageParser;
import de.iotacb.cloud.core.world.World;
import de.iotacb.cloud.utilities.input.Input;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Render;

public class Window {

	float deltaTime;

	int fpsCap, framebufferWidth, framebufferHeight, sampling;
	GLFWFramebufferSizeCallback framebufferSizeCallback;

	GpioController gpioController;
 
	Input inputHandler;

	int mouseX, mouseY, lastMouseX, lastMouseY;

	FrameTimer timer;
	GLFWWindowFocusCallback windowFocusCallback;

	long windowId, mainMonitor;

	boolean windowResizable, windowFullscreen, windowFocused, vsync;

	Vec windowSize, mouseLocation, lastMouseLocation;

	GLFWWindowSizeCallback windowSizeCallback;

	String windowTitle;
	double windowWidth, windowHeight;
	World world, prevWorld;

	public Window(int width, int height, String title) {
		createWindow(width, height, title, false, false);
	}

	public Window(int width, int height, String title, boolean fullscreen, boolean resizable) {
		createWindow(width, height, title, fullscreen, resizable);
	}

	private void createWindow(double windowWidth, double windowHeight, String windowTitle, boolean fullscreen,
			boolean resizable) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.windowTitle = windowTitle;
		this.sampling = 0;
		this.windowSize = new Vec(windowWidth, windowHeight);
		this.mouseLocation = new Vec();
		this.lastMouseLocation = new Vec();

		this.world = new World(this) {
			@Override
			public void draw() {
				drawEntities();
			}

			@Override
			public void initialize() {
			}

			@Override
			public void update() {
				updateEntities();
			}
		};

		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws Exception {
		if (!glfwInit())

		setWindowHints();
		makeWindow();
		setGLFW();
		inputHandler = new Input(this.windowId, this, System.getProperty("os.name").contains("Windows"));
		timer = new FrameTimer();
		Render.window = this;
	}

	public void initGpio() {
		if (!System.getProperty("os.name").contains("Windows"))
			this.gpioController = GpioFactory.getInstance();
	}

	public void destroy() {
		framebufferSizeCallback.free();
		windowSizeCallback.free();
		windowFocusCallback.free();
		glfwTerminate();
		glfwDestroyWindow(windowId);
	}

	private void drawScreen() throws Exception {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, (int) windowWidth, (int) windowHeight);
		glOrtho(0, windowWidth, windowHeight, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);

		glClear(GL_COLOR_BUFFER_BIT);
		world.draw();
		glfwSwapBuffers(this.windowId);

		deltaTime = timer.getDelta();
		timer.updateFPS();
	}

	private void makeWindow() throws Exception {
		mainMonitor = glfwGetPrimaryMonitor();
		GLFWVidMode videoMode = glfwGetVideoMode(mainMonitor);

		if (windowFullscreen) {
			this.windowWidth = videoMode.width();
			this.windowHeight = videoMode.height();
			this.framebufferWidth = videoMode.width();
			this.framebufferHeight = videoMode.height();
			this.windowSize.set(videoMode.width(), videoMode.height());
		}

		windowId = glfwCreateWindow((int) this.windowWidth, (int) this.windowHeight, this.windowTitle,
				(this.windowFullscreen ? mainMonitor : 0), 0);
		if (windowId == 0 || windowId == NULL) {
			glfwTerminate();
		}

		glfwSetWindowPos(windowId, (int) (videoMode.width() - windowWidth) / 2,
				(int) (videoMode.height() - windowHeight) / 2);

		setCallbacks();
	}

	private void setCallbacks() {
		glfwSetFramebufferSizeCallback(windowId, framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
			@Override
			public void invoke(long windowId, int width, int height) {
				if (width > 0 && height > 0) {
					framebufferWidth = width;
					framebufferHeight = height;
				}
			}
		});

		glfwSetWindowSizeCallback(windowId, windowSizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long windowId, int width, int height) {
				if (width > 0 && height > 0) {
					windowWidth = width;
					windowHeight = height;
					windowSize.set(width, height);
				}
			}
		});

		glfwSetWindowFocusCallback(windowId, windowFocusCallback = new GLFWWindowFocusCallback() {

			@Override
			public void invoke(long windowId, boolean focused) {
				windowFocused = focused;
			}
		});
	}

	private void setGLFW() {
		glfwMakeContextCurrent(windowId);
		glfwSwapInterval(vsync ? 1 : 0);
		glfwShowWindow(windowId);
		GL.createCapabilities();
	}

	private void setWindowHints() {
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, (this.windowResizable ? GLFW_TRUE : GLFW_FALSE));
		if (sampling > 0) {
			glfwWindowHint(GLFW_SAMPLES, sampling);
		}
	}

	private void updateCursor() {
		DoubleBuffer bufferX = BufferUtils.createDoubleBuffer(1), bufferY = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(windowId, bufferX, bufferY);
		lastMouseLocation.set(mouseLocation);
		mouseLocation.set(bufferX.get(), bufferY.get());
	}

	private void updateScreen() throws Exception {
		glfwPollEvents();
		updateCursor();
		timer.updateUPS();
		world.update();
		inputHandler.update();
	}

	public void setFPSCap(int fpsCap) {
		this.fpsCap = fpsCap;
	}

	public void setIcon(String path) {
		GLFWImage image = GLFWImage.malloc();
		GLFWImage.Buffer imageBuffer = GLFWImage.malloc(1);
		ImageParser parsedImage = ImageParser.loadImage(path);
		image.set(parsedImage.width, parsedImage.height, parsedImage.image);
		imageBuffer.put(0, image);
		glfwSetWindowIcon(windowId, imageBuffer);
	}

	public void setSampling(int sampling) {
		this.sampling = sampling;
	}

	public void setTitle(String title) {
		this.windowTitle = title;
		glfwSetWindowTitle(windowId, title);
	}

	public void setVSync(boolean vsync) {
		this.vsync = vsync;
		glfwSwapInterval(vsync ? 1 : 0);
	}

	public void setWindowOpacity(float opacity) {
		glfwSetWindowOpacity(windowId, opacity);
	}

	public void setWorld(final Class<? extends World> world) {
		try {
			this.world = world.getConstructor(getClass()).newInstance(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.world.initialize();
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public void show() throws Exception {
		while (!glfwWindowShouldClose(windowId)) {
			Sync.sync(fpsCap);
			updateScreen();
			drawScreen();
			timer.updateCounters();
		}
		destroy();
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public float getDeltaTime() {
		return deltaTime;
	}

	public int getFPS() {
		return timer.getFPS();
	}

	public int getFpsCap() {
		return fpsCap;
	}

	public GpioController getGpioController() {
		return gpioController;
	}

	public Input getInput() {
		return inputHandler;
	}

	public Vec getLastMouseLocation() {
		return lastMouseLocation;
	}

	public int getLastMouseX() {
		return lastMouseX;
	}

	public int getLastMouseY() {
		return lastMouseY;
	}

	public Vec getMouseLocation() {
		return mouseLocation;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public World getPrevWorld() {
		return prevWorld;
	}

	public double getWindowHeight() {
		return windowHeight;
	}

	public Vec getWindowSize() {
		return windowSize;
	}

	public double getWindowWidth() {
		return windowWidth;
	}

	public World getWorld() {
		return world;
	}

	public boolean isVsync() {
		return vsync;
	}

	public boolean isWindowClosing() {
		return glfwWindowShouldClose(windowId);
	}

	public boolean isWindowFocused() {
		return windowFocused;
	}

	public boolean isWindowFullscreen() {
		return windowFullscreen;
	}

	public boolean isWindowResizable() {
		return windowResizable;
	}

}
