package de.iotacb.homepi.screens.main;

import java.awt.Color;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Vector;
import de.iotacb.cloud.utilities.render.Render;

public class d extends Entity {

	public d(Window window) {
		super(window);
	}
	
	Vector vel;

	@Override
	public void initialize() {
		vel = new Vector(6, 0);
	}

	@Override
	public void update() {
		moveWithGamepad(600, .1, 0);
	}

	@Override
	public void draw() {
		Render.circleCentered(location, 30, Color.red);
	}
}
