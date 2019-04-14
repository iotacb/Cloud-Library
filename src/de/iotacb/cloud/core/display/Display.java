package de.iotacb.cloud.core.display;

import de.iotacb.cloud.core.display.input.InputHandler;
import de.iotacb.cloud.core.exceptions.InitializeException;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Vector;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.cloud.utilities.time.Timer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import java.awt.*;
import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Display {

    public int displayWidth, displayHeight, frameBufferWidth, frameBufferHeight, currentFrames, maxFrames;
    public double mouseX, mouseY, deltaTime;

    public String windowTitle;

    public Screen currentScreen, previousScreen;

    public InputHandler inputHandler;

    public boolean windowResizeable, windowFullscreen;

    private double lastTime, diffTime;
    private long mainMonitor, windowID, frameCounter;

    GLFWFramebufferSizeCallback framebufferSizeCallback;
    GLFWWindowSizeCallback windowSizeCallback;

    Timer frameTimer;

    public Display(final int displayWidth, final int displayHeight, final String windowTitle, final boolean windowResizeable, final boolean windowFullscreen) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.windowTitle = windowTitle;
        this.windowResizeable = windowResizeable;
        this.windowFullscreen = windowFullscreen;
        try {
            initialize();
        } catch (InitializeException e) {
            e.printStackTrace();
        }
    }

    public Display(final Vector dimensions, final String windowTitle, final boolean windowResizeable, final boolean windowFullscreen) {
        this.displayWidth = dimensions.getXInt();
        this.displayHeight = dimensions.getYInt();
        this.windowTitle = windowTitle;
        this.windowResizeable = windowResizeable;
        this.windowFullscreen = windowFullscreen;
        try {
            initialize();
        } catch (InitializeException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws InitializeException {
        if (!glfwInit()) throw new InitializeException("Unable to initialize GLFW.");
        setWindowHints();
        createWindow();
        setGLFW();
        inputHandler = new InputHandler(windowID);
        frameTimer = new Timer();
        Render.display = this;
        deltaTime = System.currentTimeMillis();
    }

    private void setWindowHints() {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, (windowResizeable ? GLFW_TRUE : GLFW_FALSE));
        glfwWindowHint(GLFW_SAMPLES, 4);
    }

    private void setCallbacks() {
        glfwSetFramebufferSizeCallback(windowID, framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long windowID, int width, int height) {
                if (width > 0 && height > 0 && frameBufferWidth != width && frameBufferHeight != height) {
                    frameBufferWidth = width;
                    frameBufferHeight = height;
                }
            }
        });

        glfwSetWindowSizeCallback(windowID, windowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long windowID, int width, int height) {
                if (width > 0 && height > 0 && displayWidth != width && displayHeight != height) {
                    displayWidth = width;
                    displayHeight = height;
                }
            }
        });
    }

    private void createWindow() throws InitializeException {
        mainMonitor = glfwGetPrimaryMonitor();
        GLFWVidMode vidMode = glfwGetVideoMode(mainMonitor);

        if (windowFullscreen) {
            displayWidth = vidMode.width();
            displayHeight = vidMode.height();
            frameBufferWidth = vidMode.width();
            frameBufferHeight = vidMode.height();
        }

        windowID = glfwCreateWindow(displayWidth, displayHeight, windowTitle, (windowFullscreen ? mainMonitor : 0), 0);

        if (windowID == 0) throw new InitializeException("Unable to create a window");

        glfwSetWindowPos(windowID, (vidMode.width() - displayWidth) / 2, (vidMode.height() - displayHeight) / 2);

        setCallbacks();
    }

    private void setGLFW() {
        glfwMakeContextCurrent(windowID);
        glfwSwapInterval(0);
        glfwShowWindow(windowID);
        GL.createCapabilities();
    }

    private void updateCursor() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1),
                yBuffer = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(windowID, xBuffer, yBuffer);
        mouseX = xBuffer.get();
        mouseY = yBuffer.get();
    }

    private void updateComponents() throws InitializeException {
        updateCursor();
        if (currentScreen == null) throw new InitializeException("Screen is not set");
        currentScreen.update();
        inputHandler.update();
    }

    private void drawComponents() throws InitializeException {
        long time = System.nanoTime();
        diffTime = ((time - lastTime) / 1000000);
        lastTime = time;
        deltaTime = Maths.clamp(diffTime / 10, 0.0001, Double.MAX_VALUE);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwPollEvents();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glViewport(0, 0, displayWidth, displayHeight);
        glOrtho(0, displayWidth, displayHeight, 0, -1, 1);

        frameCounter++;
        if (frameTimer.havePassed(1000)) {
            currentFrames = (int) frameCounter;
            frameCounter = 0;
        }

        if (currentScreen == null) throw new InitializeException("Screen is not set");
        currentScreen.draw();
        glfwSwapBuffers(windowID);
    }

    /**
     * Show the window
     * @throws InitializeException
     */
    public void show() throws InitializeException {
        while (!glfwWindowShouldClose(windowID)) {
            Sync.sync(maxFrames);
            updateComponents();
            drawComponents();
        }
    }

    /**
     * Set the current screen
     * @param screen The wanted screen
     */
    public void setScreen(final Class<? extends Screen> screen) {
        try {
            currentScreen = screen.getConstructor(getClass()).newInstance(this);
            currentScreen.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the opacity of the window
     * @param opacity The opacity of the window
     */
    public void setOpacity(final float opacity) {
        glfwSetWindowOpacity(windowID, opacity);
    }

    /**
     * Set the background color of the window
     * @param color The background color
     */
    public void setBackgroundColor(final Color color) {
        float red = color.getRed() / 255.0F;
        float green = color.getGreen() / 255.0F;
        float blue = color.getBlue() / 255.0F;
        float alpha = color.getAlpha() / 255.0F;
        glClearColor(red, green, blue, alpha);
    }

    /**
     * Set maximal amount of frames
     * @param maxFrames The maximal amount of frames
     */
    public void setMaxFrames(final int maxFrames) {
        this.maxFrames = maxFrames;
    }

}
