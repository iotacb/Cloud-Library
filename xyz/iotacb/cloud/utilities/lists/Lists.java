package xyz.iotacb.cloud.utilities.lists;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xyz.iotacb.cloud.core.display.Display;

public class Lists {
	
	public static List<Object> genList(final Class<?> clazz, final int amount) {
		return genList(clazz, amount, null);
	}
	
	public static List<Object> genList(final Class<?> clazz, final int amount, final Object parameters) {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < amount; i++) {
			try {
				list.add(clazz.getConstructor(parameters.getClass()).newInstance(parameters));
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
