package de.iotacb.homepi2.gui.keyboard;

import java.util.ArrayList;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.homepi2.menus.shop.ShopMenu;

public class KeyboardRenderer {

	Window window;
	ShopMenu menu;

	List<Key> keys;

	int keySize, keySizeAndSpace;

	public KeyboardRenderer(Window window, ShopMenu menu) {
		this.window = window;
		this.menu = menu;
		this.keys = new ArrayList<Key>();

		keySize = (int) (((window.getWindowWidth() - window.getWindowHeight()) / 10) - 8);
		keySizeAndSpace = keySize + 6;

		addKeys();
	}

	public void draw() {
		this.keys.forEach(Key::draw);
	}

	public void update() {
		this.keys.forEach(Key::update);
	}

	void addKeys() {
		String firstRow = "qwertzuiop";
		int rowOneX = keySize / 2;
		for (String c : firstRow.split("")) {
			keys.add(new Key(window, " " + c.toUpperCase() + " ", new Vec(keySize, keySize),
					new Vec(rowOneX + window.getWindowWidth() / 2 - (keySizeAndSpace * firstRow.length()) / 2,
							window.getWindowHeight() - keySizeAndSpace * 3),
					this));
			rowOneX += keySizeAndSpace;
		}

		String secondRow = "asdfghjkl";
		int rowTwoX = keySize / 2;
		for (String c : secondRow.split("")) {
			keys.add(new Key(window, " " + c.toUpperCase() + " ", new Vec(keySize, keySize),
					new Vec(rowTwoX + window.getWindowWidth() / 2 - (keySizeAndSpace * secondRow.length()) / 2,
							window.getWindowHeight() - keySizeAndSpace * 2),
					this));
			rowTwoX += keySizeAndSpace;
		}

		String thirdRow = "yxcvbnm";
		int rowThreeX = keySize / 2;
		for (String c : thirdRow.split("")) {
			keys.add(new Key(window, " " + c.toUpperCase() + " ", new Vec(keySize, keySize),
					new Vec(rowThreeX + window.getWindowWidth() / 2 - (keySizeAndSpace * thirdRow.length()) / 2,
							window.getWindowHeight() - keySizeAndSpace),
					this));
			rowThreeX += keySizeAndSpace;
		}

		keys.add(new Key(window, "Entfernen", new Vec(keySize * 2, keySize),
				new Vec(window.getWindowWidth() / 2 - (keySizeAndSpace * firstRow.length()) / 2 + keySize,
						window.getWindowHeight() - keySizeAndSpace * 4),
				this));
		keys.add(new Key(window, "Loeschen", new Vec(keySize * 2, keySize),
				new Vec(window.getWindowWidth() / 2 + (keySizeAndSpace * firstRow.length()) / 2 - keySizeAndSpace,
						window.getWindowHeight() - keySizeAndSpace * 4),
				this));
		keys.add(new Key(window, "Leerzeichen", new Vec(keySize * 2, keySize),
				new Vec(window.getWindowWidth() / 2 - keySizeAndSpace, window.getWindowHeight() - keySizeAndSpace * 4),
				this));
		keys.add(new Key(window, "Enter", new Vec(keySize * 2, keySize),
				new Vec(window.getWindowWidth() / 2 + keySize, window.getWindowHeight() - keySizeAndSpace * 4), this));
	}

	void addToInput(String c) {
		if (c.equalsIgnoreCase("leerzeichen")) {
			menu.input.addLetter(" ");
		} else if (c.equalsIgnoreCase("entfernen")) {
			menu.input.delLetter();
		} else if (c.equalsIgnoreCase("loeschen")) {
			menu.del();
		} else if (c.equalsIgnoreCase("enter")) {
			menu.add();
		} else {
			menu.input.addLetter(c.trim());
		}
	}

}
