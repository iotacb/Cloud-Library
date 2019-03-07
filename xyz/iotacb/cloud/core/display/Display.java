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
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.nglfwGetFramebufferSize;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
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
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.MemoryUtil;

import xyz.iotacb.cloud.core.display.input.KeyHandler;
import xyz.iotacb.cloud.core.exceptions.CloudCreateException;
import xyz.iotacb.cloud.core.exceptions.CloudInitializeException;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class Display {

	public VectorI displayDimensions, frameBufferSize; // Stores window and framebuffer sizes
	
	public boolean resizeable, windowed;
	
	public long mainMonitor, window;

	public String title;
	
	public Screen mainScreen;
	
	public int mouseX, mouseY, swapInterval = 0;
	
	public KeyHandler keyHandler;
	
	GLCapabilities caps;
	GLFWFramebufferSizeCallback frameBufferCallback;
	GLFWWindowSizeCallback windowSizeCallback;
	
	public Display (final VectorI dimensions, final String title, final boolean resizeable, final boolean windowed) throws CloudInitializeException, CloudCreateException {
		this.displayDimensions = dimensions;
		this.frameBufferSize = new VectorI();
		this.title = title;
		this.resizeable = resizeable;
		this.windowed = windowed;
		init();
	}
	
	/**
	 * Sets up the window
	 * @throws CloudInitializeException 
	 * @throws CloudCreateException 
	 */
	void init() throws CloudInitializeException, CloudCreateException {
		if (!glfwInit()) throw new CloudInitializeException("Failed to initialize GLFW");
		
		// Window settings
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, (resizeable ? GLFW_TRUE : GLFW_FALSE));
		glfwWindowHint(GLFW_SAMPLES, 4);
		
		//
		
		mainMonitor = glfwGetPrimaryMonitor();
		
		GLFWVidMode vidMode = glfwGetVideoMode(mainMonitor);
		
		if (!windowed) {
			displayDimensions = new VectorI(vidMode.width(), vidMode.height());
			frameBufferSize = new VectorI(vidMode.width(), vidMode.height());
		}
		
		window = glfwCreateWindow(displayDimensions.x, displayDimensions.y, title, (windowed ? 0L : mainMonitor), 0L);
		
		if (window == 0L) throw new CloudCreateException("Failed to create Window");
		
		// Callbacks
		
		glfwSetFramebufferSizeCallback(window, frameBufferCallback = new GLFWFramebufferSizeCallback() {
			
			@Override
			public void invoke(long window, int width, int height) {
				if (width > 0 && height > 0 && (frameBufferSize.x != width || frameBufferSize.y != height)) {
					frameBufferSize.set(width, height);
				}
			}
		});
		
		glfwSetWindowSizeCallback(window, windowSizeCallback = new GLFWWindowSizeCallback() {
			
			@Override
			public void invoke(long window, int width, int height) {
				if (width > 0 && height > 0 && (displayDimensions.x != width || displayDimensions.y != height)) {
					displayDimensions.set(width, height);
				}
			}
		});
		
		//
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(0);
		glfwShowWindow(window);
		
		IntBuffer bufferSize = BufferUtils.createIntBuffer(2);
		nglfwGetFramebufferSize(window, MemoryUtil.memAddress(bufferSize), MemoryUtil.memAddress(bufferSize) + 4);
		frameBufferSize = new VectorI(bufferSize.get(0), bufferSize.get(1));
		
		caps = GL.createCapabilities();
		
		keyHandler = new KeyHandler(window);
		
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE);
	}
	
	/**
	 * Handles the shutdown of the program
	 */
	void shutdown() {
		glfwTerminate();
		System.exit(0);
	}
	
	/**
	 * Updates the cursor location
	 */
	void updateCursor() {
		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, x, y);
		mouseX = (int) x.get();
		mouseY = (int) y.get();
	}
	
	/**
	 * Updates the display and all of it's components
	 * @throws CloudInitializeException 
	 */
	public void update() throws CloudInitializeException {
		while (!glfwWindowShouldClose(window)) {
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			glViewport(0, 0, displayDimensions.x, displayDimensions.y);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, displayDimensions.x, displayDimensions.y, 0, -1, 1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			
			if (mainScreen == null) throw new CloudInitializeException("Failed to initialize Screen");
			
			mainScreen.update(); // Update before drawing
			mainScreen.draw();
			mainScreen.screenDimensions = displayDimensions;
			
			glfwSwapBuffers(window);
			
			keyHandler.update();
			updateCursor();
			
			glfwPollEvents();
			
			glfwSwapInterval(swapInterval);
		}
		shutdown();
	}
	
	/**
	 * Sets the main screen wich will be rendered as default
	 * @param mainScreen
	 */
	public void setMainScreen(final Screen mainScreen) {
		this.mainScreen = mainScreen;
		this.mainScreen.init();
	}
	
	public void setMainScreen(final Class<? extends Screen> clazz) {
		try {
			this.mainScreen = (Screen) clazz.getConstructor(this.getClass()).newInstance(this);
			this.mainScreen.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//
	
	/**
	 * Sets the opacity of the Display (0.0 - 1.0)
	 * @param opacity
	 */
	public void setDisplayOpacity(final float opacity) {
		glfwSetWindowOpacity(window, opacity);
	}
	
	/**
	 * Sets the background color of the screen
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
	 * Set the swap interval
	 * @param swapInterval
	 */
	public void setSwapInterval(int swapInterval) {
		this.swapInterval = swapInterval;
	}
}
