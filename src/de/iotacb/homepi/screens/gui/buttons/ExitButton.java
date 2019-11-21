package de.iotacb.homepi.screens.gui.buttons;

import java.io.File;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.files.FilesWriter;

public class ExitButton extends Button {
	
	public ExitButton(Window window, int x, int y, String title) {
		super(window, x, y, title);
	}
	
	@Override
	public void onClick() {
		FilesWriter.writeString(new File("gpio.txt"), "true");
		System.exit(0);
		super.onClick();
	}
	
	@Override
	public void update() {
		super.update();
	}

}
