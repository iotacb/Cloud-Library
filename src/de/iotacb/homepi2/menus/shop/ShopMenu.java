package de.iotacb.homepi2.menus.shop;

import java.util.ArrayList;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.world.World;
import de.iotacb.cloud.utilities.files.FilesReader;
import de.iotacb.cloud.utilities.files.FilesWriter;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.homepi2.bg.BGRenderer;
import de.iotacb.homepi2.gui.ShopInput;
import de.iotacb.homepi2.gui.ShopSendButton;
import de.iotacb.homepi2.gui.WorldChangeButton;
import de.iotacb.homepi2.gui.keyboard.KeyboardRenderer;
import de.iotacb.homepi2.menus.main.MainMenu;

public class ShopMenu extends World {
	
	WorldChangeButton back;
	ShopSendButton send;
	
	public ShopInput input;
	
	Text itemText;
	
	List<String> items;
	
	BGRenderer bg;
	KeyboardRenderer kr;

	public ShopMenu(Window window) {
		super(window);
	}

	@Override
	public void initialize() {
		this.items = new ArrayList<String>();
		
		load();
		
		this.back = new WorldChangeButton(window, "Back", new Vec(120, 40), MainMenu.class);
		this.send = new ShopSendButton(window, "Senden", new Vec(window.getWindowWidth() - 120, 40), items);
		this.input = new ShopInput(window, "zum Beispiel: Wasser", 300, new Vec(200, 40), new Vec(20, 70));
		this.itemText = new Text(10);
		
		addEntities(back, input, send);
		
		this.bg = new BGRenderer(window);
		this.kr = new KeyboardRenderer(window, this);
		
	}

	@Override
	public void update() {
		kr.update();
		bg.update();
		updateEntities();
		
		if (window.getInput().getKeyDown(Keys.KEY_ENTER)) {
			add();
		}
		
		if (window.getInput().getKeyDown(Keys.KEY_DELETE)) {
			del();
		}
	}

	@Override
	public void draw() {
		bg.draw();
		drawEntities();
		
		itemText.drawText(20, 120, "Einkaufsliste, Items: " + (items.size() == 15 ? items.size() + " Minuten" : items.size()));
		
		int yCount = 140;
		int xCount = 30;
		for (String item : items) {
			itemText.drawText(xCount, yCount, item);
			yCount += 15;
		}
		
		kr.draw();
	}
	
	void sendBtn() {
		delEntity(ShopSendButton.class);
		this.send = new ShopSendButton(window, "Senden", new Vec(window.getWindowWidth() - 120, 40), items);
		addEntity(send);
	}
	
	public void add() {
		if (input.getTypedText() != "") {
			items.add("- " + input.getTypedText());
			input.clear();
			sendBtn();
			save();
		}
	}
	
	public void del() {
		if (items.size() > 0) {
			items.remove(items.size() - 1);
			sendBtn();
			save();
		}
	}
	
	void load() {
		this.items = FilesReader.readAsStringList("homepi/files/shop.txt");
	}
	
	void save() {
		FilesWriter.writeStringList("homepi/files/shop.txt", items);
	}

}
