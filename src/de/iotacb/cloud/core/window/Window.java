package de.iotacb.cloud.core.window;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowOpacity;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
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

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import de.iotacb.cloud.utilities.input.InputHandler;
import de.iotacb.cloud.utilities.math.Vector;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.cloud.utilities.time.Timer;

public class Window {

	public int windowWidth, windowHeight;
	public Vector windowSize;

	int framebufferWidth, framebufferHeight;

	public int mouseX, mouseY, lastMouseX, lastMouseY;
	public Vector mouseLocation;

	public String windowTitle;

	boolean windowResizable;
	boolean windowFullscreen;

	public long windowId, mainMonitor;

	GLFWFramebufferSizeCallback framebufferSizeCallback;
	GLFWWindowSizeCallback windowSizeCallback;

	public World world, prevWorld;

	public InputHandler inputHandler;

	Timer frameTimer;

	double lastLoopTime, timeCount;
	public int fps, fpsCount, ups, upsCount, fpsCap;

	private void createWindow(int windowWidth, int windowHeight, String windowTitle, boolean windowResizable,
			boolean windowFullscreen) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.windowTitle = windowTitle;
		this.windowResizable = windowResizable;
		this.windowFullscreen = windowFullscreen;
		this.windowSize = new Vector(windowWidth, windowHeight);
		this.mouseLocation = new Vector();

		this.world = new World(this) {
			@Override
			public void update() {
				updateEntities();
			}

			@Override
			public void initialize() {
			}

			@Override
			public void draw() {
				drawEntities();
			}
		};

		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Window(int windowWidth, int windowHeight, String windowTitle, boolean windowResizable,
			boolean windowFullscreen) {
		createWindow(windowWidth, windowHeight, windowTitle, windowResizable, windowFullscreen);
	}

	public Window(int windowWidth, int windowHeight, String windowTitle) {
		createWindow(windowWidth, windowHeight, windowTitle, false, false);
	}

	public Window(Vector windowSize, String windowTitle, boolean windowResizable, boolean windowFullscreen) {
		createWindow((int) windowSize.x, (int) windowSize.y, windowTitle, windowResizable, windowFullscreen);
	}

	public Window(Vector windowSize, String windowTitle) {
		createWindow((int) windowSize.x, (int) windowSize.y, windowTitle, false, false);
	}

	public Window(int windowWidth, int windowHeight) {
		createWindow(windowWidth, windowHeight, "Cloud window", false, false);
	}

	public Window(Vector windowSize) {
		createWindow((int) windowSize.x, (int) windowSize.y, "Cloud window", false, false);
	}

	private void initialize() throws Exception {
		if (!glfwInit())
			throw new Exception("Couldn't initialize GLFW. Error: #001");

		setWindowHints();
		makeWindow();
		setGLFW();
		this.inputHandler = new InputHandler(this.windowId, this, System.getProperty("os.name").contains("Windows"));
		this.frameTimer = new Timer();
		this.lastLoopTime = glfwGetTime();
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
		if (windowId == 0)
			throw new Exception("Couldn't create window. Error: #002");

		glfwSetWindowPos(windowId, (videoMode.width() - this.windowWidth) / 2,
				(videoMode.height() - this.windowHeight) / 2);

		setCallbacks();
	}

	private void setWindowHints() {
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, (this.windowResizable ? GLFW_TRUE : GLFW_FALSE));
		glfwWindowHint(GLFW_SAMPLES, 4);
	}

	private void setCallbacks() {
		glfwSetFramebufferSizeCallback(windowId, framebufferSizeCallback = new GLFWFramebufferSizeCallback() {

			@Override
			public void invoke(long windowId, int width, int height) {
				if (width > 0 && height > 0 && framebufferWidth != width && framebufferHeight != height) {
					framebufferWidth = width;
					framebufferHeight = height;
					windowWidth = width;
					windowHeight = height;
					windowSize.set(width, height);
				}
			}
		});

		glfwSetWindowSizeCallback(windowId, windowSizeCallback = new GLFWWindowSizeCallback() {

			@Override
			public void invoke(long windowId, int width, int height) {
				if (width > 0 && height > 0 && windowWidth != width && windowHeight != height) {
					windowWidth = width;
					windowHeight = height;
					windowSize.set(width, height);
				}
			}
		});
	}

	private void setGLFW() {
		glfwMakeContextCurrent(this.windowId);
		glfwSwapInterval(0);
		glfwShowWindow(this.windowId);
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
		if (world == null)
			throw new Exception("No screen set. Error: #003:update");
		updateCounters();
		updateCursor();
		this.world.update();
		this.inputHandler.update();
		upsCount++;
	}

	private void drawScreen() throws Exception {
		if (world == null)
			throw new Exception("No screen set. Error: #003:draw");

		glfwPollEvents();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, windowWidth, windowHeight);
		glOrtho(0, windowWidth, windowHeight, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);

		glClear(GL_COLOR_BUFFER_BIT);
		world.draw();
		glfwSwapBuffers(this.windowId);

		fpsCount++;
	}

	public void show() throws Exception {
		while (!glfwWindowShouldClose(windowId)) {
			Sync.sync(fpsCap);
			updateScreen();
			drawScreen();
		}
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

	public void setFPSCap(int fpsCap) {
		this.fpsCap = fpsCap;
	}

	public void updateCounters() {
		if (timeCount > 1f) {
			fps = fpsCount;
			fpsCount = 0;

			ups = upsCount;
			upsCount = 0;

			timeCount -= 1f;
		}
	}

	public double getDelta() {
		double time = glfwGetTime();
		double delta = (time - lastLoopTime);
		lastLoopTime = time;
		timeCount += delta;
		return delta;
	}

	public double getLastLoopTime() {
		return lastLoopTime;
	}

}
