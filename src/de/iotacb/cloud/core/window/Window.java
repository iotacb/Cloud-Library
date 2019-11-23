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
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import de.iotacb.cloud.utilities.input.InputHandler;
import de.iotacb.cloud.utilities.math.vector.VectorI;
import de.iotacb.cloud.utilities.render.Render;

public class Window {

	public int windowWidth, windowHeight;
	public VectorI windowSize;

	public int mouseX, mouseY, lastMouseX, lastMouseY;
	public VectorI mouseLocation;

	public String windowTitle;

	public long windowId, mainMonitor;

	public World world, prevWorld;

	public InputHandler inputHandler;
	public FrameTimer timer;

	public int fpsCap;
	
	public float deltaTime;

	int framebufferWidth, framebufferHeight, sampling;

	boolean windowResizable, windowFullscreen, windowFocused, vsync;

	GLFWFramebufferSizeCallback framebufferSizeCallback;
	GLFWWindowSizeCallback windowSizeCallback;
	GLFWWindowFocusCallback windowFocusCallback;

	private void createWindow(int windowWidth, int windowHeight, String windowTitle, boolean windowResizable,
			boolean windowFullscreen, boolean vsync, int sampling) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.windowTitle = windowTitle;
		this.windowResizable = windowResizable;
		this.windowFullscreen = windowFullscreen;
		this.sampling = sampling <= 0 ? 0 : sampling;
		this.windowSize = new VectorI(windowWidth, windowHeight);
		this.mouseLocation = new VectorI();

		this.world = new World(this) {
			@Override
			public void update() { updateEntities(); }

			@Override
			public void initialize() {}

			@Override
			public void draw() { drawEntities(); }
		};

		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Window(int windowWidth, int windowHeight, String windowTitle, boolean windowResizable,
			boolean windowFullscreen, boolean vsync, int sampling) {
		createWindow(windowWidth, windowHeight, windowTitle, windowResizable, windowFullscreen, vsync, sampling);
	}

	public Window(int windowWidth, int windowHeight, String windowTitle, int sampling) {
		createWindow(windowWidth, windowHeight, windowTitle, false, false, false, sampling);
	}

	public Window(VectorI windowSize, String windowTitle, boolean windowResizable, boolean windowFullscreen, boolean vsync, int sampling) {
		createWindow((int) windowSize.x, (int) windowSize.y, windowTitle, windowResizable, windowFullscreen, vsync, sampling);
	}

	public Window(VectorI windowSize, String windowTitle, int sampling) {
		createWindow((int) windowSize.x, (int) windowSize.y, windowTitle, false, false, false, sampling);
	}

	public Window(int windowWidth, int windowHeight, int sampling) {
		createWindow(windowWidth, windowHeight, "Cloud window", false, false, false, sampling);
	}

	public Window(VectorI windowSize, int sampling) {
		createWindow((int) windowSize.x, (int) windowSize.y, "Cloud window", false, false, false, sampling);
	}

	private void initialize() throws Exception {
		if (!glfwInit())
			throw new Exception("Failed to initialize GLFW");

		setWindowHints();
		makeWindow();
		setGLFW();
		this.inputHandler = new InputHandler(this.windowId, this, System.getProperty("os.name").contains("Windows"));
		this.timer = new FrameTimer();
		timer.init();
		Render.window = this;
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

		windowId = glfwCreateWindow(this.windowWidth, this.windowHeight, this.windowTitle,
				(this.windowFullscreen ? mainMonitor : 0), 0);
		if (windowId == 0 || windowId == NULL) {
			glfwTerminate();
			throw new Exception("Failed to create window");
		}

		glfwSetWindowPos(windowId, (videoMode.width() - windowWidth) / 2,
				(videoMode.height() - windowHeight) / 2);

		setCallbacks();
	}

	private void setWindowHints() {
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, (this.windowResizable ? GLFW_TRUE : GLFW_FALSE));
		if (sampling > 0) {
			glfwWindowHint(GLFW_SAMPLES, sampling);
		}
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
					world.windowWidth = width;
					world.windowHeight = height;
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

	private void updateCursor() {
		DoubleBuffer bufferX = BufferUtils.createDoubleBuffer(1), bufferY = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(windowId, bufferX, bufferY);
		this.lastMouseX = this.mouseX;
		this.lastMouseY = this.mouseY;
		this.mouseX = (int) bufferX.get();
		this.mouseY = (int) bufferY.get();
		this.mouseLocation.set(this.mouseX, this.mouseY);
	}

	private void updateScreen() throws Exception {
		glfwPollEvents();
		updateCursor();
		timer.updateUPS();
		world.update();
		inputHandler.update();
		
	}

	private void drawScreen() throws Exception {
		if (world == null)
			throw new Exception("No screen set. Error: #003:draw");

		{
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glViewport(0, 0, windowWidth, windowHeight);
			glOrtho(0, windowWidth, windowHeight, 0, -1, 1);
			glMatrixMode(GL_MODELVIEW);
			
			glClear(GL_COLOR_BUFFER_BIT);
			world.draw();
			glfwSwapBuffers(this.windowId);
		}
		
		deltaTime = timer.getDelta();
		timer.updateFPS();
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
	
	public void destroy() {
		framebufferSizeCallback.free();
		windowSizeCallback.free();
		windowFocusCallback.free();
		glfwTerminate();
		glfwDestroyWindow(windowId);
	}

	public void setWorld(final Class<? extends World> world) {
		try {
			this.world = world.getConstructor(getClass()).newInstance(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.world.initialize();
	}

	public void setWindowOpacity(float opacity) {
		glfwSetWindowOpacity(windowId, opacity);
	}
	
	public void setTitle(String title) {
		this.windowTitle = title;
		glfwSetWindowTitle(windowId, title);
	}

	public void setFPSCap(int fpsCap) {
		this.fpsCap = fpsCap;
	}
	
    public void setVSync(boolean vsync) {
        this.vsync = vsync;
        glfwSwapInterval(vsync ? 1 : 0);
    }
	
	public float getDeltaTime() {
		return deltaTime;
	}
	
	public int getFPS() {
		return timer.getFPS();
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
	
	public boolean isWindowClosing() {
		return glfwWindowShouldClose(windowId);
	}
	
	public boolean isVsync() {
		return vsync;
	}

}
