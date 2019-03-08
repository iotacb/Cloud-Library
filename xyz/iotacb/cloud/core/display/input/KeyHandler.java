package xyz.iotacb.cloud.core.display.input;

import static org.lwjgl.glfw.GLFW.*;

public class KeyHandler {
	
	public boolean[] keys; // boolean for each key
	
	long window; // ID of window
	
	public KeyHandler(final long window) {
		this.window = window;
		this.keys = new boolean[GLFW_KEY_LAST];
		
		for (int i = 0; i < GLFW_KEY_LAST; i++) {
			keys[i] = false;
		}
	}
	
	/**
	 * Returns if given key is pressed
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyDown(final int keyCode) {
		return glfwGetKey(window, keyCode) == 1;
	}
	
	/**
	 * Returns if given mouse button is pressed
	 * @param mouseButton
	 * @return
	 */
	public boolean isMouseButtonDown(final int mouseButton) {
		return glfwGetMouseButton(window, mouseButton) == 1;
	}
	
	/**
	 * Returns if given key had been pressed
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyPressed(final int keyCode) {
		return (isKeyDown(keyCode) && !keys[keyCode]);
	}
	
	/**
	 * Returns if given key had been released
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyReleased(final int keyCode) {
		return (isKeyDown(keyCode) && keys[keyCode]);
	}
	
	/**
	 * Returns if no buttons are pressed
	 * @return
	 */
	public boolean nonePressed() {
		boolean r = true;
		for (int i = 0; i < GLFW_KEY_LAST; i++) {
			if (keys[i]) {
				r = false;
			}
		}
		return r;
	}
	
	/**
	 * Updates all keys
	 */
	public void update() {
		for (int i = 0; i < GLFW_KEY_LAST; i++) {
			keys[i] = isKeyDown(i);
		}
	}
	

}
