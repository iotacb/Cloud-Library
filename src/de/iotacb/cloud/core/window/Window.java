package de.iotacb.cloud.core.window;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import de.iotacb.cloud.core.scene.Scene;
import de.iotacb.cloud.core.window.icon.ImageParser;
import de.iotacb.cloud.utilities.input.Input;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.cloud.utilities.render.font.FontManager;

public class Window {

	private float deltaTime;

	private int fpsCap, sampling;
	private GLFWFramebufferSizeCallback framebufferSizeCallback;

	private Input inputHandler;

	private FrameTimer timer;
	private GLFWWindowFocusCallback windowFocusCallback;

	private long windowId, mainMonitor;

	private boolean windowResizable, windowFullscreen, windowFocused, vsync;

	private Vec windowSize, mouseLocation, lastMouseLocation;

	private GLFWWindowSizeCallback windowSizeCallback;

	private String windowTitle;
	private double windowWidth, windowHeight;
	private Scene scene, prevScene;

	public Window(final int width, final int height, final String title) {
		createWindow(width, height, title, false, false);
	}

	public Window(final int width, final int height, final String title, final boolean fullscreen, final boolean resizable) {
		createWindow(width, height, title, fullscreen, resizable);
	}

	private void createWindow(final int windowWidth, final int windowHeight, final String windowTitle, final boolean fullscreen, final boolean resizable) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.windowTitle = windowTitle;
		this.sampling = 0;
		this.windowSize = new Vec(windowWidth, windowHeight);
		this.mouseLocation = new Vec();
		this.lastMouseLocation = new Vec();

		this.scene = new Scene(this) {
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
		if (!GLFW.glfwInit()) return;
		setWindowHints();
		makeWindow();
		setGLFW();
		inputHandler = new Input(this.windowId, this, System.getProperty("os.name").contains("Windows"));
		timer = new FrameTimer();
		Render.window = this;
		FontManager.instance = new FontManager();
	}

	public final void destroy() {
		framebufferSizeCallback.free();
		windowSizeCallback.free();
		windowFocusCallback.free();
		GLFW.glfwTerminate();
		GLFW.glfwDestroyWindow(windowId);
	}

	private void drawScreen() throws Exception {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glViewport(0, 0, (int) windowWidth, (int) windowHeight);
		GL11.glOrtho(0, windowWidth, windowHeight, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		scene.draw();
		GLFW.glfwSwapBuffers(this.windowId);

		deltaTime = timer.getDelta();
		timer.updateFPS();
	}

	private void makeWindow() throws Exception {
		mainMonitor = GLFW.glfwGetPrimaryMonitor();
		final GLFWVidMode videoMode = GLFW.glfwGetVideoMode(mainMonitor);

		if (windowFullscreen) {
			this.windowWidth = videoMode.width();
			this.windowHeight = videoMode.height();
			videoMode.width();
			videoMode.height();
			this.windowSize.set(videoMode.width(), videoMode.height());
		}

		windowId = GLFW.glfwCreateWindow((int) this.windowWidth, (int) this.windowHeight, this.windowTitle, (this.windowFullscreen ? mainMonitor : 0), 0);
		if (windowId == 0 || windowId == MemoryUtil.NULL) {
			GLFW.glfwTerminate();
		}

		GLFW.glfwSetWindowPos(windowId, (int) (videoMode.width() - windowWidth) / 2, (int) (videoMode.height() - windowHeight) / 2);

		setCallbacks();
	}

	private void setCallbacks() {
		GLFW.glfwSetFramebufferSizeCallback(windowId, framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
			@Override
			public void invoke(long windowId, int width, int height) {
				if (width > 0 && height > 0) {
				}
			}
		});

		GLFW.glfwSetWindowSizeCallback(windowId, windowSizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long windowId, int width, int height) {
				if (width > 0 && height > 0) {
					windowWidth = width;
					windowHeight = height;
					windowSize.set(width, height);
				}
			}
		});

		GLFW.glfwSetWindowFocusCallback(windowId, windowFocusCallback = new GLFWWindowFocusCallback() {

			@Override
			public void invoke(long windowId, boolean focused) {
				windowFocused = focused;
			}
		});
	}

	private void setGLFW() {
		GLFW.glfwMakeContextCurrent(windowId);
		GLFW.glfwSwapInterval(vsync ? 1 : 0);
		GLFW.glfwShowWindow(windowId);
		GL.createCapabilities();
	}

	private void setWindowHints() {
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, (this.windowResizable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE));
		if (sampling > 0) {
			GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, sampling);
		}
	}

	private void updateCursor() {
		final DoubleBuffer bufferX = BufferUtils.createDoubleBuffer(1), bufferY = BufferUtils.createDoubleBuffer(1);
		
		GLFW.glfwGetCursorPos(windowId, bufferX, bufferY);
		mouseLocation.set((float)bufferX.get(), (float)bufferY.get());
		lastMouseLocation.set(mouseLocation);
	}

	private void updateScreen() throws Exception {
		GLFW.glfwPollEvents();
		updateCursor();
		timer.updateUPS();
		scene.update();
		inputHandler.update();
	}

	public final void setFPSCap(final int fpsCap) {
		this.fpsCap = fpsCap;
	}

	public final void setIcon(final String path) {
		final GLFWImage image = GLFWImage.malloc();
		final GLFWImage.Buffer imageBuffer = GLFWImage.malloc(1);
		final ImageParser parsedImage = ImageParser.loadImage(path);
		image.set(parsedImage.width, parsedImage.height, parsedImage.image);
		imageBuffer.put(0, image);
		GLFW.glfwSetWindowIcon(windowId, imageBuffer);
	}

	public final void setSampling(final int sampling) {
		this.sampling = sampling;
	}

	public final void setTitle(final String title) {
		this.windowTitle = title;
		GLFW.glfwSetWindowTitle(windowId, title);
	}

	public final void setVSync(final boolean vsync) {
		this.vsync = vsync;
		GLFW.glfwSwapInterval(vsync ? 1 : 0);
	}

	public final void setWindowOpacity(final float opacity) {
		GLFW.glfwSetWindowOpacity(windowId, opacity);
	}

	public final void setScene(final Class<? extends Scene> scene) {
		try {
			this.scene = scene.getConstructor(getClass()).newInstance(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.scene.initialize();
	}

	public final void setScene(final Scene scene) {
		this.scene = scene;
	}

	public final void show() throws Exception {
		while (!GLFW.glfwWindowShouldClose(windowId)) {
			Sync.sync(fpsCap);
			updateScreen();
			drawScreen();
			timer.updateCounters();
		}
		destroy();
	}

	public final String getWindowTitle() {
		return windowTitle;
	}

	public final float getDeltaTime() {
		return deltaTime;
	}

	public final int getFPS() {
		return timer.getFPS();
	}

	public final int getFpsCap() {
		return fpsCap;
	}

	public final Input getInput() {
		return inputHandler;
	}

	public final Vec getLastMouseLocation() {
		return lastMouseLocation;
	}

	public final int getLastMouseX() {
		return lastMouseLocation.getXI();
	}

	public final int getLastMouseY() {
		return lastMouseLocation.getYI();
	}

	public final Vec getMouseLocation() {
		return mouseLocation;
	}

	public final int getMouseX() {
		return mouseLocation.getXI();
	}

	public final int getMouseY() {
		return mouseLocation.getYI();
	}

	public final Scene getPrevScene() {
		return prevScene;
	}

	public final double getWindowHeight() {
		return windowHeight;
	}

	public final Vec getWindowSize() {
		return windowSize;
	}

	public final double getWindowWidth() {
		return windowWidth;
	}

	public final Scene getScene() {
		return scene;
	}

	public final boolean isVsync() {
		return vsync;
	}

	public final boolean isWindowClosing() {
		return GLFW.glfwWindowShouldClose(windowId);
	}

	public final boolean isWindowFocused() {
		return windowFocused;
	}

	public final boolean isWindowFullscreen() {
		return windowFullscreen;
	}

	public final boolean isWindowResizable() {
		return windowResizable;
	}

}
