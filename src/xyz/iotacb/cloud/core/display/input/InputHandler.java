package xyz.iotacb.cloud.core.display.input;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class InputHandler {

	public boolean[] keys;
	public boolean[] buttons;

	long window;

	public boolean hasGamepad;
	
	/**
	 * Create the input handler which communicates with the window ID
	 * @param window
	 */
	public InputHandler(final long window) {
		this.window = window;
		this.keys = new boolean[GLFW_KEY_LAST];
		for (int i = 0; i < GLFW_KEY_LAST; i++) {
			keys[i] = false;
		}
		if (glfwGetJoystickName(GLFW_JOYSTICK_1) == null) {
			this.hasGamepad = false;
		} else {
			this.hasGamepad = true;
		}
		if (hasGamepad) {
			this.buttons = new boolean[GLFW_GAMEPAD_BUTTON_LAST];
			for (int i = 0; i < GLFW_GAMEPAD_BUTTON_LAST; i++) {
				buttons[i] = false;
			}
		}
	}

	/**
	 * Checks if a key on the keyboard is pressed down
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyDown(final int keyCode) {
		return glfwGetKey(window, keyCode) == 1;
	}

	/**
	 * Checks if a button on a gamepad is pressed down
	 * @param buttonCode
	 * @return
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
	 * Checks if a button on the mouse is pressed down
	 * @param mouseButton
	 * @return
	 */
	public boolean isMouseButtonDown(final int mouseButton) {
		return glfwGetMouseButton(window, mouseButton) == 1;
	}

	/**
	 * Checks if a key on the keyboard had been pressed
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyPressed(final int keyCode) {
		return (isKeyDown(keyCode) && !keys[keyCode]);
	}

	/**
	 * Checks if a key on the keyboard had been released
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyReleased(final int keyCode) {
		return (isKeyDown(keyCode) && keys[keyCode]);
	}
	
	/**
	 * Checks if no keys on the keyboard are pressed
	 * @return
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
	 * Checks if no buttons on a gamepad are pressed
	 * @return
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

	/**
	 * Updates the inputs
	 */
	public void update() {
		if (glfwGetJoystickName(GLFW_JOYSTICK_1) == null) {
			this.hasGamepad = false;
		} else {
			this.hasGamepad = true;
		}
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
	 * Get the x axe value of the left joystick
	 * @return
	 */
	public float getLeftJoystickX() {
		if (!hasGamepad)
			return 0;
		FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
		float a = axes.get(0);
		return a;
	}

	/**
	 * Get the y axe value of the left joystick
	 * @return
	 */
	public float getLeftJoystickY() {
		if (!hasGamepad)
			return 0;
		FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
		float a = axes.get(1);
		return a;
	}

	/**
	 * Get the x axe value if the right joystick
	 * @return
	 */
	public float getRightJoystickX() {
		if (!hasGamepad)
			return 0;
		FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
		float a = axes.get(2);
		return a;
	}

	/**
	 * Get the y axe value of the right joystick
	 * @return
	 */
	public float getRightJoystickY() {
		if (!hasGamepad)
			return 0;
		FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
		float a = axes.get(3);
		return a;
	}

	/**
	 * Get the value of the lower left trigger
	 * @return
	 */
	public float getLeftTrigger() {
		if (!hasGamepad)
			return 0;
		FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
		float a = axes.get(4);
		return a;
	}

	/**
	 * Get the value of the lower right trigger
	 * @return
	 */
	public float getRightTrigger() {
		if (!hasGamepad)
			return 0;
		FloatBuffer axes = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
		float a = axes.get(5);
		return a;
	}

	/**
	 * Checks if the lower left trigger is pressed
	 * @return
	 */
	public boolean isLeftTriggerPressed() {
		if (!hasGamepad)
			return false;
		return getLeftTrigger() == 1;
	}

	/** 
	 * Checks if the lower right trigger is pressed
	 * @return
	 */
	public boolean isRightTriggerPressed() {
		if (!hasGamepad)
			return false;
		return getRightTrigger() == 1;
	}

}
