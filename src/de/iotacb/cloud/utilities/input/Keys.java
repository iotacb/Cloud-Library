package de.iotacb.cloud.utilities.input;

import static org.lwjgl.glfw.GLFW.*;

import java.lang.reflect.Field;

public class Keys {
	
	public static int KEY_F1 = GLFW_KEY_F1;
	public static int KEY_F2 = GLFW_KEY_F2;
	public static int KEY_F3 = GLFW_KEY_F3;
	public static int KEY_F4 = GLFW_KEY_F4;
	public static int KEY_F5 = GLFW_KEY_F5;
	public static int KEY_F6 = GLFW_KEY_F6;
	public static int KEY_F7 = GLFW_KEY_F7;
	public static int KEY_F8 = GLFW_KEY_F8;
	public static int KEY_F9 = GLFW_KEY_F9;
	public static int KEY_F10 = GLFW_KEY_F10;
	public static int KEY_F11 = GLFW_KEY_F11;
	public static int KEY_F12 = GLFW_KEY_F12;
	public static int KEY_F13 = GLFW_KEY_F13;
	public static int KEY_F14 = GLFW_KEY_F14;
	public static int KEY_F15 = GLFW_KEY_F15;
	public static int KEY_F16 = GLFW_KEY_F16;
	public static int KEY_F17 = GLFW_KEY_F17;
	public static int KEY_F18 = GLFW_KEY_F18;
	public static int KEY_F19 = GLFW_KEY_F19;
	public static int KEY_F20 = GLFW_KEY_F20;
	public static int KEY_F21 = GLFW_KEY_F21;
	public static int KEY_F22 = GLFW_KEY_F22;
	public static int KEY_F23 = GLFW_KEY_F23;
	public static int KEY_F24 = GLFW_KEY_F24;
	public static int KEY_F25 = GLFW_KEY_F25;

	public static int KEY_0 = GLFW_KEY_0;
	public static int KEY_1 = GLFW_KEY_1;
	public static int KEY_2 = GLFW_KEY_2;
	public static int KEY_3 = GLFW_KEY_3;
	public static int KEY_4 = GLFW_KEY_4;
	public static int KEY_5 = GLFW_KEY_5;
	public static int KEY_6 = GLFW_KEY_6;
	public static int KEY_7 = GLFW_KEY_7;
	public static int KEY_8 = GLFW_KEY_8;
	public static int KEY_9 = GLFW_KEY_9;
	
	public static int KEY_A = GLFW_KEY_A;
	public static int KEY_B = GLFW_KEY_B;
	public static int KEY_C = GLFW_KEY_C;
	public static int KEY_D = GLFW_KEY_D;
	public static int KEY_E = GLFW_KEY_E;
	public static int KEY_F = GLFW_KEY_F;
	public static int KEY_G = GLFW_KEY_G;
	public static int KEY_H = GLFW_KEY_H;
	public static int KEY_I = GLFW_KEY_I;
	public static int KEY_J = GLFW_KEY_J;
	public static int KEY_K = GLFW_KEY_K;
	public static int KEY_L = GLFW_KEY_L;
	public static int KEY_M = GLFW_KEY_M;
	public static int KEY_N = GLFW_KEY_N;
	public static int KEY_O = GLFW_KEY_O;
	public static int KEY_P = GLFW_KEY_P;
	public static int KEY_Q = GLFW_KEY_Q;
	public static int KEY_R = GLFW_KEY_R;
	public static int KEY_S = GLFW_KEY_S;
	public static int KEY_T = GLFW_KEY_T;
	public static int KEY_U = GLFW_KEY_U;
	public static int KEY_V = GLFW_KEY_V;
	public static int KEY_W = GLFW_KEY_W;
	public static int KEY_X = GLFW_KEY_X;
	public static int KEY_Y = GLFW_KEY_Y;
	public static int KEY_Z = GLFW_KEY_Z;
	
	public static int KEY_ESCAPE = GLFW_KEY_ESCAPE;
	public static int KEY_GRAVE = GLFW_KEY_GRAVE_ACCENT;
	public static int KEY_TAB = GLFW_KEY_TAB;
	public static int KEY_CAPS = GLFW_KEY_CAPS_LOCK;
	public static int KEY_SHIFT1 = GLFW_KEY_LEFT_SHIFT;
	public static int KEY_CTRL1 = GLFW_KEY_LEFT_CONTROL;
	public static int KEY_SUPER1 = GLFW_KEY_LEFT_SUPER;
	public static int KEY_ALT1 = GLFW_KEY_LEFT_ALT;
	public static int KEY_SPACE = GLFW_KEY_SPACE;
	public static int KEY_ALT2 = GLFW_KEY_RIGHT_ALT;
	public static int KEY_SUPER2 = GLFW_KEY_RIGHT_SUPER;
	public static int KEY_MENU = GLFW_KEY_MENU;
	public static int KEY_CTRL2 = GLFW_KEY_RIGHT_CONTROL;
	public static int KEY_SHIFT2 = GLFW_KEY_RIGHT_SHIFT;
	public static int KEY_ENTER = GLFW_KEY_ENTER;
	public static int KEY_BACKSPACE = GLFW_KEY_BACKSPACE;
	
	public static int KEY_PRINT = GLFW_KEY_PRINT_SCREEN;
	public static int KEY_SCROLL = GLFW_KEY_SCROLL_LOCK;
	public static int KEY_PAUSE = GLFW_KEY_PAUSE;
	public static int KEY_INSERT = GLFW_KEY_INSERT;
	public static int KEY_HOME = GLFW_KEY_HOME;
	public static int KEY_PAGEUP = GLFW_KEY_PAGE_UP;
	public static int KEY_DELETE = GLFW_KEY_DELETE;
	public static int KEY_END = GLFW_KEY_END;
	public static int KEY_PAGEDOWN = GLFW_KEY_PAGE_DOWN;
	
	public static int KEY_UP = GLFW_KEY_UP;
	public static int KEY_LEFT = GLFW_KEY_LEFT;
	public static int KEY_RIGHT = GLFW_KEY_RIGHT;
	public static int KEY_DOWN = GLFW_KEY_DOWN;
	
	public static int KEY_NPDECIMAL = GLFW_KEY_KP_DECIMAL;
	public static int KEY_NPDIVIDE = GLFW_KEY_KP_DIVIDE;
	public static int KEY_NPMULTIPLY = GLFW_KEY_KP_MULTIPLY;
	public static int KEY_NPSUBTRACT = GLFW_KEY_KP_SUBTRACT;
	public static int KEY_NPADD = GLFW_KEY_KP_ADD;
	public static int KEY_NPENTER = GLFW_KEY_KP_ENTER;
	public static int KEY_NPEQUALS = GLFW_KEY_KP_EQUAL;
	public static int KEY_NP0 = GLFW_KEY_KP_0;
	public static int KEY_NP1 = GLFW_KEY_KP_1;
	public static int KEY_NP2 = GLFW_KEY_KP_2;
	public static int KEY_NP3 = GLFW_KEY_KP_3;
	public static int KEY_NP4 = GLFW_KEY_KP_4;
	public static int KEY_NP5 = GLFW_KEY_KP_5;
	public static int KEY_NP6 = GLFW_KEY_KP_6;
	public static int KEY_NP7 = GLFW_KEY_KP_7;
	public static int KEY_NP8 = GLFW_KEY_KP_8;
	public static int KEY_NP9 = GLFW_KEY_KP_9;
	
	public static int getKey(String keyName) {
		int key = -1;
		for (Field f : Keys.class.getDeclaredFields()) {
			if (keyName.equalsIgnoreCase(f.getName().split("_")[1])) {
				try {
					key = (int) f.get(f);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return key;
	}

}