package de.iotacb.homepi2.menus.main;

import java.awt.Color;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.world.World;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.homepi2.bg.BGRenderer;
import de.iotacb.homepi2.bg.particles.ParticleGenerator;
import de.iotacb.homepi2.gui.WorldChangeButton;
import de.iotacb.homepi2.menus.info.InfoMenu;
import de.iotacb.homepi2.menus.shop.ShopMenu;

public class MainMenu extends World {

	WorldChangeButton info, shop;

	Text logo;
	
	ParticleGenerator gen;
	BGRenderer bg;

	public MainMenu(Window window) {
		super(window);
	}
	
	@Override
	public void initialize() {
		this.info = new WorldChangeButton(window, "Info", new Vec(200, window.getWindowHeight() / 2 - 70), InfoMenu.class);
		this.shop = new WorldChangeButton(window, "Shop", new Vec(200, window.getWindowHeight() / 2 - 20), ShopMenu.class);

		addEntities(info, shop);
		
		this.bg = new BGRenderer(window);
		
		this.logo = new Text((int) ((window.getWindowWidth() - window.getWindowHeight()) / 6));
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

		logo.drawText(5, 5, "HomePI", Color.white);
	}

}
