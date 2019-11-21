package de.iotacb.homepi.screens.shopping;

import java.awt.Color;
import java.io.File;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.window.World;
import de.iotacb.cloud.utilities.files.FilesReader;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.homepi.screens.gui.buttons.Button;
import de.iotacb.homepi.screens.gui.inputs.Input;

public class ShoppingScreen extends World {
	
	List<String> items;
	
	Text text;
	
	Input input;
	
	Button send;
	
	int sendBtnOffsetter = 20;
	
	public ShoppingScreen(Window window) {
		super(window);
	}

	@Override
	public void initialize() {
		this.items = FilesReader.readAsStringList(new File("items.txt"));
		this.text = new Text(10);
		this.input = new Input(window, 100, 200, "zum Beispiel: Brot");
		this.send = new SendButton(window, 100, 280 + (items.size() * sendBtnOffsetter), items);
		
		addEntity(new BackButton(window, 20, 20, null));
		addEntity(send);
		addEntity(input);
	}

	@Override
	public void update() {
		updateEntities();
		
		if (window.inputHandler.isKeyPressed(Keys.ENTER)) {
			if (input.typedText != "") {
				items.add("- " + (input.typedText.equalsIgnoreCase("tischdecke") ? input.typedText + " stinkt!!!" : input.typedText));
				input.reset();
				save();
			}
		}
		
		if (window.inputHandler.isKeyPressed(Keys.DELETE)) {
			if (items.size() > 0)
				items.remove(items.size() - 1);
			save();
		}
		
		
	}

	@Override
	public void draw() {
		drawEntities();
		
		text.drawText(100, 250, "Liste: 	Items: " + items.size() + (items.size() == 15 ? " Minuten" : ""), Color.white);
		
		int yCount = 280;
		for (String item : items) {
			text.drawText(120, yCount, item, Color.white);
			yCount += 20;
		}
	}
	
	void save() {
		delEntity(BackButton.class);
		delEntity(SendButton.class);
		addEntity(new BackButton(window, 20, 20, items));
		this.send = new SendButton(window, 100, 280 + (items.size() * sendBtnOffsetter), items);
		addEntity(send);
	}

}
