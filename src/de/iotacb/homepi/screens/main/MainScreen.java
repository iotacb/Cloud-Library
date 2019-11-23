package de.iotacb.homepi.screens.main;

import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.window.World;
import de.iotacb.cloud.utilities.render.Image;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.homepi.screens.gui.buttons.ExitButton;
import de.iotacb.homepi.screens.gui.buttons.ScreenChangeButton;
import de.iotacb.homepi.screens.gui.particles.ParticleGenerator;
import de.iotacb.homepi.screens.info.InfoScreen;
import de.iotacb.homepi.screens.muell.MuellScreen;
import de.iotacb.homepi.screens.shopping.ShoppingScreen;

public class MainScreen extends World {

	Text logo, text;

	Image img;
	
	ParticleGenerator gen;

	public MainScreen(Window window) {
		super(window);
	}

	@Override
	public void initialize() {
		this.logo = new Text(60);
		this.text = new Text(10);
		
		this.gen = new ParticleGenerator(window);

		this.img = new Image(window, new File("assets/textures/homepi-logo.png"));

		addEntity(new ScreenChangeButton(window, windowWidth / 2 - 415, windowHeight / 2 + 80, "Muell",
				MuellScreen.class));
		addEntity(new ScreenChangeButton(window, windowWidth / 2 - 205, windowHeight / 2 + 80, "Infos",
				InfoScreen.class));
		addEntity(new ScreenChangeButton(window, windowWidth / 2 + 5, windowHeight / 2 + 80, "Einkaufsliste",
				ShoppingScreen.class));
		addEntity(new ExitButton(window, windowWidth / 2 + 215, windowHeight / 2 + 80, "Exit "));
	}

	@Override
	public void update() {
		System.out.println(window.getFPS());
		gen.update();
		updateEntities();
	}

	@Override
	public void draw() {
		gen.draw();
		drawLogo();
		drawEntities();
	}

	void drawLogo() {
		String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
		text.drawText(windowWidth / 2 - text.getHWidth(date) - 60, windowHeight / 2, date, Color.white);
		img.drawImage(windowWidth / 2 - 128, windowHeight / 2 - 200);
	}

}
