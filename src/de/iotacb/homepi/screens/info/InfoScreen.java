package de.iotacb.homepi.screens.info;

import java.awt.Color;
import java.io.File;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.window.World;
import de.iotacb.cloud.utilities.files.FilesReader;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.homepi.screens.gui.buttons.ScreenChangeButton;
import de.iotacb.homepi.screens.gui.particles.ParticleGenerator;
import de.iotacb.homepi.screens.main.MainScreen;

public class InfoScreen extends World {
	
	List<String> infos;
	
	Text text;
	
	ParticleGenerator gen;
	
	public InfoScreen(Window window) {
		super(window);
	}

	@Override
	public void initialize() {
		this.text = new Text(15);
		this.infos = FilesReader.readAsStringList(new File("infos.txt"));
		
		this.gen = new ParticleGenerator(window);
		
		addEntity(new ScreenChangeButton(window, 20, 20, "Zurueck", MainScreen.class));
	}

	@Override
	public void update() {
		gen.update();
		updateEntities();
	}

	@Override
	public void draw() {
		gen.draw();
		drawEntities();
		
		int yCount = 0;
		for (String line : infos) {
			text.drawText(windowWidth / 2 - text.getHWidth(line), windowHeight / 2 - 100 + yCount, line, Color.white);
			yCount += 20;
		}
	}

}
