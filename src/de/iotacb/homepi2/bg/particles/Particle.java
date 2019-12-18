package de.iotacb.homepi2.bg.particles;

import java.awt.Color;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Render;

public class Particle extends Entity {

	Vec vel;
	
	int P_SIZE = 10;
	
	public Particle(Window window) {
		super(window);
	}

	@Override
	public void initialize() {
		this.vel = new Vec().randomize(-4, 4, -4, 4);
		
		location.randomize(P_SIZE / 2, window.getWindowWidth() - P_SIZE / 2, P_SIZE / 2, window.getWindowHeight() - P_SIZE / 2);
	}

	@Override
	public void update() {
		if (location.x < P_SIZE / 2 || location.x > window.getWindowWidth() - P_SIZE / 2) {
			vel.x *= -1;
		}
		if (location.y < P_SIZE / 2 || location.y > window.getWindowHeight() - P_SIZE / 2) {
			vel.y *= -1;
		}
		location.add(vel);
	}

	@Override
	public void draw() {
		Render.polygonCentered(location, P_SIZE, 6, Color.red);
	}

}
