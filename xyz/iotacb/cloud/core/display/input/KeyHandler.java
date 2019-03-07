package xyz.iotacb.cloud.core.display.input;

import static org.lwjgl.glfw.GLFW.*;

public class KeyHandler {
	
	public boolean[] keys;
	
	long window;
	
	public KeyHandler(final long window) {
		this.window = window;
		this.keys = new boolean[GLFW_KEY_LAST];
		
		for (int i = 0; i < GLFW_KEY_LAST; i++) {
			keys[i] = false;
		}
	}
	
	/**
	 * Returns true as long the key is pressed
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyDown(final int keyCode) {
		return glfwGetKey(window, keyCode) == 1;
	}
	
	/**
	 * Returns true as long the key is pressed
	 * @param mouseButton
	 * @return
	 */
	public boolean isMouseButtonDown(final int mouseButton) {
		return glfwGetMouseButton(window, mouseButton) == 1;
	}
	
	/**
	 * Returns true when the key got pressed
	 * 
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyPressed(final int keyCode) {
		return (isKeyDown(keyCode) && !keys[keyCode]);
	}
	
	/**
	 * Returns true when the key got released
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyReleased(final int keyCode) {
		return (isKeyDown(keyCode) && keys[keyCode]);
	}
	
	/**
	 * Updates the keys
	 */
	public void update() {
		for (int i = 0; i < GLFW_KEY_LAST; i++) {
			keys[i] = isKeyDown(i);
		}
	}
	
	/**
	 * Returns true when no key is pressed
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

}
