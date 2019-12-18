 package de.iotacb.homepi2.menus.info;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.world.World;
import de.iotacb.cloud.utilities.files.FilesReader;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.homepi2.bg.BGRenderer;
import de.iotacb.homepi2.gui.WorldChangeButton;
import de.iotacb.homepi2.menus.main.MainMenu;

public class InfoMenu extends World {
	
	WorldChangeButton back;
	
	List<String> infos;
	
	Text infoDrawText;
	
	BGRenderer bg;

	public InfoMenu(Window window) {
		super(window);
	}

	@Override
	public void initialize() {
		this.back = new WorldChangeButton(window, "Back", new Vec(120, 40), MainMenu.class);
		
		this.bg = new BGRenderer(window);
		
		addEntity(back);
		
		this.infos = new ArrayList<String>();
		load();
		
		this.infoDrawText = new Text(20);
		
	}

	@Override
	public void update() {
		bg.update();
		updateEntities();
	}

	@Override
	public void draw() {
		bg.draw();
		drawEntities();
		
		int yCount = 0;
		for (String info : infos) {
			infoDrawText.drawText(window.getWindowWidth() / 2 - infoDrawText.getHWidth(info), window.getWindowHeight() / 2 + yCount - (20 * infos.size()) / 2, info, Color.white);
			yCount+=25;
		}
	}
	
	void load() {
		this.infos = FilesReader.readAsStringList("homepi/files/infos.txt");
	}

}
