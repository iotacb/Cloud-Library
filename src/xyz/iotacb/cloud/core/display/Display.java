package xyz.iotacb.cloud.core.display;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
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
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.nglfwGetFramebufferSize;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Color;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import xyz.iotacb.cloud.core.display.input.InputHandler;
import xyz.iotacb.cloud.core.exceptions.CloudCreateException;
import xyz.iotacb.cloud.core.exceptions.CloudInitializeException;
import xyz.iotacb.cloud.core.gui.Button;
import xyz.iotacb.cloud.utilities.color.Colors;
import xyz.iotacb.cloud.utilities.files.Files;
import xyz.iotacb.cloud.utilities.math.Numbers;
import xyz.iotacb.cloud.utilities.math.Vector;
import xyz.iotacb.cloud.utilities.rendering.Render;
import xyz.iotacb.cloud.utilities.time.Timer;

public class Display {

	/**
	 * Display and Framebuffer dimensions
	 */
	public Vector displayDimensions, frameBufferDimensions;

	/**
	 * Booleand if the display is fullscreen or if it should be resizeable
	 */
	boolean resizeable, fullscreen;

	/**
	 * Stores window ID and saves the main monitor ID
	 */
	public long mainMonitor, window;

	/**
	 * Stores the title of the display
	 */
	public String title;

	/**
	 * Stores the current set screen and the prevoius used one
	 */
	public Screen currentScreen, previousScreen;

	/**
	 * Stores the mouse location, currentFPS and frame lock
	 */
	public int mouseX, mouseY, currentFPS, frameLock;

	/**
	 * Input handling
	 */
	public InputHandler inputHandler;

	/**
	 * File handling
	 */
	public Files fileHandler;

	int fpsCounter, swapInterval = 1;
	double time;

	boolean showNullPoint = false;

	GLFWFramebufferSizeCallback frameBufferCallback;
	GLFWWindowSizeCallback windowSizeCallback;

	Timer fpsTimer;
	
	ArgumentHandler argumentHandler;

	/**
	 * Create the display
	 * 
	 * @param dimensions
	 * @param title
	 * @param resizeable
	 * @param fullscreen
	 * @throws CloudInitializeException
	 * @throws CloudCreateException
	 */
	public Display(final Vector dimensions, final String title, final boolean resizeable, final boolean fullscreen, final String...arguments)
			throws CloudInitializeException, CloudCreateException {
		this.argumentHandler = new ArgumentHandler(arguments);
		if (!this.argumentHandler.useArguments) {
			this.displayDimensions = dimensions;
			this.frameBufferDimensions = Vector.empty();
			this.title = title;
			this.resizeable = resizeable;
			this.fullscreen = fullscreen;
		} else {
			this.displayDimensions = this.argumentHandler.displayDimensions;
			this.frameBufferDimensions = Vector.empty();
			this.title = this.argumentHandler.title;
			this.resizeable = this.argumentHandler.resizeable;
			this.fullscreen = this.argumentHandler.fullscreen;
		}
		init();
	}
	
	/**
	 * Create the display
	 * @param width
	 * @param height
	 * @param title
	 * @param resizeable
	 * @param fullscreen
	 * @throws CloudInitializeException
	 * @throws CloudCreateException
	 */
	public Display(final int width, final int height, final String title, final boolean resizeable, final boolean fullscreen, final String...arguments)
			throws CloudInitializeException, CloudCreateException {
		this.argumentHandler = new ArgumentHandler(arguments);
		if (!this.argumentHandler.useArguments) {
			this.displayDimensions = Vector.createVector(width, height);
			this.frameBufferDimensions = Vector.empty();
			this.title = title;
			this.resizeable = resizeable;
			this.fullscreen = fullscreen;
		} else {
			this.displayDimensions = this.argumentHandler.displayDimensions;
			this.frameBufferDimensions = Vector.empty();
			this.title = this.argumentHandler.title;
			this.resizeable = this.argumentHandler.resizeable;
			this.fullscreen = this.argumentHandler.fullscreen;
		}
		init();
	}

	/**
	 * Initializes window stuff
	 * @throws CloudInitializeException
	 * @throws CloudCreateException
	 */
	void init() throws CloudInitializeException, CloudCreateException {
		if (!glfwInit()) {
			throw new CloudInitializeException("Failed to initialize GLFW");
		}

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, (resizeable ? GLFW_TRUE : GLFW_FALSE));
		glfwWindowHint(GLFW_SAMPLES, 4);

		fpsTimer = new Timer();

		mainMonitor = glfwGetPrimaryMonitor();

		GLFWVidMode vidMode = glfwGetVideoMode(mainMonitor);

		if (fullscreen) {
			displayDimensions.set(new Vector(vidMode.width(), vidMode.height()));
			frameBufferDimensions = new Vector(vidMode.width(), vidMode.height());
		}

		window = glfwCreateWindow((int) displayDimensions.x, (int) displayDimensions.y, title,
				(fullscreen ? mainMonitor : 0L), 0L);

		glfwSetWindowPos(window, (vidMode.width() - (int) displayDimensions.x) / 2,
				(vidMode.height() - (int) displayDimensions.y) / 2);

		if (window == 0L)
			throw new CloudCreateException("Failed to create Window");

		glfwSetFramebufferSizeCallback(window, frameBufferCallback = new GLFWFramebufferSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
				if (width > 0 && height > 0
						&& (frameBufferDimensions.x != width || frameBufferDimensions.y != height)) {
					frameBufferDimensions.set(width, height);
				}
			}
		});

		glfwSetWindowSizeCallback(window, windowSizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
				if (width > 0 && height > 0 && (displayDimensions.x != width || displayDimensions.y != height)) {
					displayDimensions.set(width, height);
					currentScreen.screenDimensions = displayDimensions;
				}
			}
		});

		glfwMakeContextCurrent(window);
		glfwSwapInterval(swapInterval);
		glfwShowWindow(window);

		IntBuffer bufferSize = BufferUtils.createIntBuffer(2);
		nglfwGetFramebufferSize(window, MemoryUtil.memAddress(bufferSize), MemoryUtil.memAddress(bufferSize) + 4);
		frameBufferDimensions = new Vector(bufferSize.get(0), bufferSize.get(1));

		GL.createCapabilities();

		inputHandler = new InputHandler(window);
		fileHandler = new Files();
	}

	/**
	 * Handles shutdown actions
	 */
	void shutdown() {
		glfwTerminate();
		System.exit(0);
	}

	/**
	 * Updates the cursor position
	 */
	void updateCursor() {
		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, x, y);
		mouseX = (int) x.get();
		mouseY = (int) y.get();
	}

	/**
	 * Update the screen and all other components
	 * @throws CloudInitializeException
	 */
	void updateComponents() throws CloudInitializeException {
		updateCursor();
		time += 0.1;
		if (currentScreen == null)
			throw new CloudInitializeException("Screen is not initialized!");

		currentScreen.update();
		inputHandler.update();
		updateCursor();
	}

	/**
	 * Draws the screen and all other components
	 * @throws CloudInitializeException
	 */
	void drawComponents() throws CloudInitializeException {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwPollEvents();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, (int) displayDimensions.x, (int) displayDimensions.y);
		glOrtho(0, displayDimensions.x, displayDimensions.y, 0, -1, 1);

		if (currentScreen == null)
			throw new CloudInitializeException("Screen is not initialized!");

		currentScreen.draw();

		fpsCounter++;
		if (fpsTimer.isOver(1000)) {
			currentFPS = fpsCounter;
			fpsCounter = 0;
		}

		glfwSwapBuffers(window);
	}

	/**
	 * Start the display update and rendering
	 * 
	 * @throws CloudInitializeException
	 */
	public void start() throws CloudInitializeException {
		while (!glfwWindowShouldClose(window)) {
			Sync.sync(frameLock);
			updateComponents();
			drawComponents();
		}
		shutdown();
	}

	/**
	 * Set the currently used screen
	 * 
	 * @param mainScreen
	 */
	public void setScreen(final Screen mainScreen) {
		this.currentScreen = mainScreen;
		this.currentScreen.init();
		for (Button b : this.currentScreen.buttonList) {
			b.setClicked(false);
		}
	}

	/**
	 * Set the currently used screen
	 * 
	 * @param clazz
	 */
	public void setScreen(final Class<? extends Screen> clazz) {
		try {
			this.currentScreen = (Screen) clazz.getConstructor(this.getClass()).newInstance(this);
			this.currentScreen.init();
			for (Button b : this.currentScreen.buttonList) {
				b.setClicked(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Change the opacity of the window (Does not work on linux)
	 * 
	 * @param opacity
	 */
	public void setDisplayOpacity(final float opacity) {
		glfwSetWindowOpacity(window, opacity);
	}

	/**
	 * Set the default background color (Is not updated)
	 * 
	 * @param color
	 */
	public void setBackgroundColor(final Color color) {
		float red = color.getRed() / 255.0F;
		float green = color.getGreen() / 255.0F;
		float blue = color.getGreen() / 255.0F;
		float alpha = color.getAlpha() / 255.0F;
		glClearColor(red, green, blue, alpha);
	}

	/**
	 * Change the swap interval of the rendering
	 * 
	 * @param swapInterval
	 */
	public void setSwapInterval(int swapInterval) {
		this.swapInterval = swapInterval;
	}

	/**
	 * Set the maximal fps
	 * 
	 * @param fpsLock
	 */
	public void setFrameLock(int frameLock) {
		this.frameLock = frameLock;
	}
}
