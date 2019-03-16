package xyz.iotacb.cloud.utilities.lists;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import a.a.a.e;
import xyz.iotacb.cloud.entity.Entity;

public class Lists {
	
	/**
	 * Create a list with a amount of classes
	 * @param clazz
	 * @param amount
	 * @return
	 */
	public static List<Object> genList(final Class<?> clazz, final int amount) {
		return genList(clazz, amount, null);
	}
	
	/**
	 * Create a list with a amount of classes
	 * @param clazz
	 * @param amount
	 * @return
	 */
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
