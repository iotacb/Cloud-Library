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
import xyz.iotacb.cloud.utilities.math.Vector3;
import xyz.iotacb.cloud.utilities.rendering.Render;
import xyz.iotacb.cloud.utilities.time.Timer;

public class Display {

	/**
	 * Display and Framebuffer dimensions
	 */
	public Vector3 displayDimensions, frameBufferDimensions;

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
	 * Stores the mouse location and the swap buffer interval
	 */
	public int mouseX, mouseY, swapInterval = 1;

	/**
	 * Input handling
	 */
	public InputHandler inputHandler;

	/**
	 * File handling
	 */
	public Files fileHandler;

	/**
	 * Stores the current fps and the max fps
	 */
	public int currentFPS, fpsLock;

	/**
	 * Set the amount of loops for the updating
	 */
	int updateLoops = 1;

	int fpsCounter;
	double time;

	int nullPoint = NullPointLocations.TOP_LEFT;
	boolean showNullPoint = false;

	GLFWFramebufferSizeCallback frameBufferCallback;
	GLFWWindowSizeCallback windowSizeCallback;

	Timer fpsTimer;

	/**
	 * Create the display
	 * @param dimensions
	 * @param title
	 * @param resizeable
	 * @param fullscreen
	 * @throws CloudInitializeException
	 * @throws CloudCreateException
	 */
	public Display(final Vector3 dimensions, final String title, final boolean resizeable, final boolean fullscreen)
			throws CloudInitializeException, CloudCreateException {
		this.displayDimensions = dimensions;
		this.frameBufferDimensions = Vector3.empty();
		this.title = title;
		this.resizeable = resizeable;
		this.fullscreen = fullscreen;
		init();
	}

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
			displayDimensions.set(new Vector3(vidMode.width(), vidMode.height()));
			frameBufferDimensions = new Vector3(vidMode.width(), vidMode.height());
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
				if (width > 0 && height > 0 && (frameBufferDimensions.x != width || frameBufferDimensions.y != height)) {
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
		frameBufferDimensions = new Vector3(bufferSize.get(0), bufferSize.get(1));

		GL.createCapabilities();

		inputHandler = new InputHandler(window);
		fileHandler = new Files();
	}

	void shutdown() {
		glfwTerminate();
		System.exit(0);
	}

	void updateCursor() {
		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, x, y);
		mouseX = (int) x.get();
		mouseY = (int) y.get();
	}

	void translate(final int point) {
		if (point == NullPointLocations.TOP_LEFT) {
			Render.translate(0, 0);
		} else if (point == NullPointLocations.TOP_RIGHT) {
			Render.translate(displayDimensions.x, 0);
		} else if (point == NullPointLocations.BOTTOM_LEFT) {
			Render.translate(0, displayDimensions.y);
		} else if (point == NullPointLocations.BOTTOM_RIGHT) {
			Render.translate(displayDimensions.x, displayDimensions.y);
		} else if (point == NullPointLocations.TOP_CENTER) {
			Render.translate(displayDimensions.getCenter().x, 0);
		} else if (point == NullPointLocations.BOTTOM_CENTER) {
			Render.translate(displayDimensions.getCenter().x, displayDimensions.y);
		} else if (point == NullPointLocations.LEFT_CENTER) {
			Render.translate(0, displayDimensions.getCenter().y);
		} else if (point == NullPointLocations.RIGHT_CENTER) {
			Render.translate(displayDimensions.x, displayDimensions.getCenter().y);
		} else if (point == NullPointLocations.CENTER) {
			Render.translate(displayDimensions.getCenter());
		}
	}

	void renderNullPoint() {
		if (showNullPoint) {
			Render.color(Colors.rainbow(0));
			Render.drawCircle(0, 0, Numbers.constrain(Math.sin(time) * 20, 5, 100));
		}
	}

	void updateComponents() throws CloudInitializeException {
		updateCursor();
		time += 0.1;
		if (currentScreen == null)
			throw new CloudInitializeException("Screen is not initialized!");

		currentScreen.update();
		inputHandler.update();
		updateCursor();
	}

	void drawComponents() throws CloudInitializeException {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwPollEvents();

		translate(nullPoint);

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

		renderNullPoint();

		glfwSwapBuffers(window);
	}

	/**
	 * Show the location of the currently used null point
	 * @param visible
	 */
	public void showNullPoint(final boolean visible) {
		showNullPoint = visible;
	}

	/**
	 * Start the display update and rendering
	 * @throws CloudInitializeException
	 */
	public void start() throws CloudInitializeException {
		while (!glfwWindowShouldClose(window)) {
			Sync.sync(fpsLock);
			for (int i = 0; i < updateLoops; i++) {
				updateComponents();
			}
			drawComponents();
		}
		shutdown();
	}

	/**
	 * Set the currently used screen
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
	 * @param opacity
	 */
	public void setDisplayOpacity(final float opacity) {
		glfwSetWindowOpacity(window, opacity);
	}

	/**
	 * Set the default background color (Is not updated)
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
	 * @param swapInterval
	 */
	public void setSwapInterval(int swapInterval) {
		this.swapInterval = swapInterval;
	}

	/**
	 * Set the maximal fps
	 * @param fpsLock
	 */
	public void setFpsLock(int fpsLock) {
		this.fpsLock = fpsLock;
	}

	/**
	 * Change the nullpoint location
	 * @param nullPoint
	 */
	public void setNullPoint(int nullPoint) {
		this.nullPoint = nullPoint;
	}
	
	/**
	 * Set the update loops
	 * @param updateLoops
	 */
	public void setUpdateLoops(int updateLoops) {
		this.updateLoops = updateLoops;
	}
}
