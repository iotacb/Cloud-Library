package de.iotacb.homepi.screens.shopping;

import java.io.File;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.files.FilesWriter;
import de.iotacb.homepi.screens.gui.buttons.Button;
import de.iotacb.homepi.screens.main.MainScreen;

public class BackButton extends Button {
	
	List<String> items;

	public BackButton(Window window, int x, int y, List<String> items) {
		super(window, x, y, "Zurueck");
		this.items = items;
	}
	
	@Override
	public void onClick() {
		if (items != null)
			FilesWriter.writeStringList(new File("items.txt"), items);
		window.setWorld(MainScreen.class);
		super.onClick();
	}

}
