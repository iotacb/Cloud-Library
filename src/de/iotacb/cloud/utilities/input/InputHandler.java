package de.iotacb.cloud.utilities.input;

import static org.lwjgl.glfw.GLFW.GLFW_GAMEPAD_BUTTON_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickAxes;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickButtons;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickName;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import de.iotacb.cloud.core.window.Window;

public class InputHandler {

    public boolean[] keys;
    public boolean[] buttons;
    
    public boolean hasGamepad, usingGamepad;
    
    public int lastKey;

    long windowId;
    
    Window window;

    boolean isWindows, lMouseDown, rMouseDown;

    public InputHandler(long windowId, Window window, boolean isWindows) {
        this.windowId = windowId;
        this.window = window;
        this.isWindows = isWindows;
        this.keys = new boolean[GLFW_KEY_LAST];
        this.hasGamepad = glfwGetJoystickName(GLFW_JOYSTICK_1) != null;
        this.usingGamepad = hasGamepad;
        for (int i = 0; i < GLFW_KEY_LAST; i++) {
            keys[i] = false;
        }
        if (hasGamepad) {
            this.buttons = new boolean[GLFW_GAMEPAD_BUTTON_LAST];
            for (int i = 0; i < GLFW_GAMEPAD_BUTTON_LAST; i++) {
                buttons[i] = false;
            }
        }
    }

    public boolean isKeyDown(int keyCode) {
        return glfwGetKey(windowId, keyCode) == 1;
    }

    public boolean isButtonDown(int buttonCode) {
        if (!hasGamepad)
            return false;
        boolean r = false;
        ByteBuffer buttons = glfwGetJoystickButtons(GLFW_JOYSTICK_1);
        for (int i = 0; i < buttons.capacity(); i++) {
            if (buttonCode != (15 | 16)) {
                if (i == buttonCode) {
                    r = (buttons.get(i) == 1);
                }
            } else {
                if (buttonCode == 15) {
                    r = isLeftTriggerPressed();
                }
                if (buttonCode == 16) {
                    r = isRightTriggerPressed();
                }
            }
        }
        return r;
    }

    public boolean isMouseButtonDown(int mouseButton) {
        boolean isPressed = glfwGetMouseButton(windowId, mouseButton) == 1;
        if (hasGamepad) {
            if (isPressed) usingGamepad = false;
        }
        return isPressed;
    }
    
    public boolean isKeyPressed(int keyCode) {
        boolean isPressed = (isKeyDown(keyCode) && !keys[keyCode]);
        if (hasGamepad) {
            if (isPressed) usingGamepad = false;
        }
        return isPressed;
    }

    public boolean isKeyReleased(int keyCode) {
        boolean isReleased = (isKeyDown(keyCode) && keys[keyCode]);
        if (hasGamepad) {
            if (isReleased) usingGamepad = false;
        }
        return isReleased;
    }

    public boolean noKeyPressed() {
        boolean r = true;
        for (int i = 0; i < GLFW_KEY_LAST; i++) {
            if (keys[i]) {
                r = false;
            }
        }
        return r;
    }

    public boolean noButtonPressed() {
        if (!hasGamepad)
            return true;
        boolean r = true;
        for (int i = 0; i < GLFW_GAMEPAD_BUTTON_LAST; i++) {
            if (buttons[i]) {
                r = false;
            }
        }
        if (isLeftTriggerPressed() || isRightTriggerPressed()) {
            r = false;
        }
        return r;
    }

    public void update() {
        this.hasGamepad = glfwGetJoystickName(GLFW_JOYSTICK_1) != null;
        if (this.window.lastMouseX != this.window.mouseX || this.window.lastMouseY != this.window.mouseY) {
        	this.usingGamepad = false;
        }
        for (int i = 0; i < GLFW_KEY_LAST; i++) {
            keys[i] = isKeyDown(i);
            if (keys[i]) {
                usingGamepad = false;
            }
            if (keys[i]) {
            	lastKey = i;
            }
        }
        isMouseButtonDown(0);
        isMouseButtonDown(1);
        if (hasGamepad) {
            if (buttons == null) {
                this.buttons = new boolean[GLFW_GAMEPAD_BUTTON_LAST];
                for (int i = 0; i < GLFW_GAMEPAD_BUTTON_LAST; i++) {
                    buttons[i] = false;
                }
            }
            for (int i = 0; i < GLFW_GAMEPAD_BUTTON_LAST; i++) {
                buttons[i] = isButtonDown(i);
                if (buttons[i]) {
                    usingGamepad = true;
                }
            }
            if (isWindows) {
            	isLeftTriggerPressed();
            	isRightTriggerPressed();
            	getLeftJoystickX();
            	getLeftJoystickY();
            	getRightJoystickX();
            	getRightJoystickY();
            }
        }
    }

    public float getLeftJoystickX() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(0);
        if (Math.abs(a) > 0.5) usingGamepad = true;
        return a;
    }

    public float getLeftJoystickY() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(1);
        if (Math.abs(a) > 0.5) usingGamepad = true;
        return a;
    }

    public float getRightJoystickX() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(2);
        if (Math.abs(a) > 0.5) usingGamepad = true;
        return a;
    }

    public float getRightJoystickY() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(3);
        if (Math.abs(a) > 0.5) usingGamepad = true;
        return a;
    }

    public float getLeftTrigger() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(4);
        return a;
    }

    public float getRightTrigger() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(5);
        return a;
    }

    public boolean isLeftTriggerPressed() {
        if (!hasGamepad)
            return false;
        boolean isPressed = getLeftTrigger() == 1;
        if (isPressed) usingGamepad = true;
        return isPressed;
    }

    public boolean isRightTriggerPressed() {
        if (!hasGamepad)
            return false;
        boolean isPressed = getRightTrigger() == 1;
        if (isPressed) usingGamepad = true;
        return isPressed;
    }

}