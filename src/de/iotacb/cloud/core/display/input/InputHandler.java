package de.iotacb.cloud.core.display.input;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class InputHandler {

    public boolean[] keys;
    public boolean[] buttons;

    long window;

    public boolean hasGamepad;

    public InputHandler(final long window) {
        this.window = window;
        this.keys = new boolean[GLFW_KEY_LAST];
        for (int i = 0; i < GLFW_KEY_LAST; i++) {
            keys[i] = false;
        }
        this.hasGamepad = glfwGetJoystickName(GLFW_JOYSTICK_1) != null;
        if (hasGamepad) {
            this.buttons = new boolean[GLFW_GAMEPAD_BUTTON_LAST];
            for (int i = 0; i < GLFW_GAMEPAD_BUTTON_LAST; i++) {
                buttons[i] = false;
            }
        }
    }

    /**
     * Check if a specific key is pressed
     * @param keyCode The wanted key
     * @return The pressed state of the key
     */
    public boolean isKeyDown(final int keyCode) {
        return glfwGetKey(window, keyCode) == 1;
    }

    /**
     * Check if a specific button on a gamepad is pressed
     * @param buttonCode The wanted button
     * @return The pressed state of the button
     */
    public boolean isButtonDown(final int buttonCode) {
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

    /**
     * Check if a mouse button is pressed
     * @param mouseButton The wanted mouse button
     * @return The pressed state of the mouse button
     */
    public boolean isMouseButtonDown(final int mouseButton) {
        return glfwGetMouseButton(window, mouseButton) == 1;
    }

    /**
     * Check if a key got pressed
     * @param keyCode The wanted key
     * @return If the key had been pressed
     */
    public boolean isKeyPressed(final int keyCode) {
        return (isKeyDown(keyCode) && !keys[keyCode]);
    }

    /**
     * Check if a key got released
     * @param keyCode The wanted key
     * @return If the key had been released
     */
    public boolean isKeyReleased(final int keyCode) {
        return (isKeyDown(keyCode) && keys[keyCode]);
    }

    /**
     * Check if no keys are pressed
     * @return If no key is pressed
     */
    public boolean noKeyPressed() {
        boolean r = true;
        for (int i = 0; i < GLFW_KEY_LAST; i++) {
            if (keys[i]) {
                r = false;
            }
        }
        return r;
    }

    /**
     * Check if no buttons on a gamepad are pressed
     * @return If no button is pressed
     */
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
        for (int i = 0; i < GLFW_KEY_LAST; i++) {
            keys[i] = isKeyDown(i);
        }
        if (hasGamepad) {
            if (buttons == null) {
                this.buttons = new boolean[GLFW_GAMEPAD_BUTTON_LAST];
                for (int i = 0; i < GLFW_GAMEPAD_BUTTON_LAST; i++) {
                    buttons[i] = false;
                }
            }
            for (int i = 0; i < GLFW_GAMEPAD_BUTTON_LAST; i++) {
                buttons[i] = isButtonDown(i);
            }
        }
    }

    /**
     * Get the x axe value of the left joystick of a gamepad
     * @return The x axe value
     */
    public float getLeftJoystickX() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(0);
        return a;
    }

    /**
     * Get the y axe value of the left joystick of a gamepad
     * @return The y axe value
     */
    public float getLeftJoystickY() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(1);
        return a;
    }

    /**
     * Get the x axe value of the right joystick of a gamepad
     * @return The x axe value
     */
    public float getRightJoystickX() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(2);
        return a;
    }

    /**
     * Get the y axe value of the right joystick of a gamepad
     * @return The y axe value
     */
    public float getRightJoystickY() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(3);
        return a;
    }

    /**
     * Get the press value of the left trigger of a gamepad
     * @return The press value
     */
    public float getLeftTrigger() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(4);
        return a;
    }

    /**
     * Get the press value of the right trigger of a gamepad
     * @return The press value
     */
    public float getRightTrigger() {
        if (!hasGamepad)
            return 0;
        FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
        float a = axes.get(5);
        return a;
    }

    /**
     * Check if the left trigger of a gamepad is pressed
     * @return If the trigger is pressed
     */
    public boolean isLeftTriggerPressed() {
        if (!hasGamepad)
            return false;
        return getLeftTrigger() == 1;
    }

    /**
     * Check if the right trigger of a gamepad is pressed
     * @return If the trigger is pressed
     */
    public boolean isRightTriggerPressed() {
        if (!hasGamepad)
            return false;
        return getRightTrigger() == 1;
    }

}