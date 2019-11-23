package de.iotacb.homepi.screens.muell;

import java.awt.Color;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.window.World;
import de.iotacb.cloud.utilities.files.FilesReader;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.cloud.utilities.time.Timer;
import de.iotacb.homepi.screens.gui.buttons.ScreenChangeButton;
import de.iotacb.homepi.screens.gui.particles.ParticleGenerator;
import de.iotacb.homepi.screens.main.MainScreen;

public class MuellScreen extends World {
	
	Timer time;
	
	DateFormat df;
	Date date;
	
	HashMap<String, String> trashDays;
	
	Text text;
	
	ParticleGenerator gen;

	public MuellScreen(Window window) {
		super(window);
	}

	@Override
	public void initialize() {
		this.time = new Timer();
		this.df = new SimpleDateFormat("dd.MM");
		this.date = new Date();
		this.text = new Text(30);
		
		this.trashDays = new HashMap<String, String>();
		
		this.gen = new ParticleGenerator(window);
		
		addEntity(new ScreenChangeButton(window, 20, 20, "Zurueck", MainScreen.class));
		
		addTrashDays();
	}

	@Override
	public void update() {
		gen.update();
		updateEntities();
	}

	@Override
	public void draw() {
		gen.draw();
		drawTrashDay();
		drawEntities();
	}
	
	void drawTrashDay() {
		if (time.havePassed(3600)) {
			this.date = new Date();
		}
		String format = df.format(date);
		if (trashDays.containsKey(format)) {
			text.drawText(windowWidth / 2 - text.getHWidth(format + ": " + trashDays.get(format)), windowHeight / 2, format + ": " + trashDays.get(format), Color.white);
		} else {
			text.drawText(windowWidth / 2 - text.getHWidth("Kein Muell muss raus."), windowHeight / 2, "Kein Muell muss raus.", Color.white);
		}
	}
	
	void addTrashDays() {
		List<String> lines = FilesReader.readAsStringList(new File("trashDays.txt"));
		for (String line : lines) {
			String day = line.trim().split(":")[0].split("\\.")[0];
			String month = line.trim().split(":")[0].split("\\.")[1];
			String type = line.trim().split(":")[1];
			trashDays.put(day + "." + month, type);
		}
	}

}
