package de.iotacb.homepi.screens.gui.particles;

import java.awt.Color;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.vector.VectorD;
import de.iotacb.cloud.utilities.math.vector.VectorI;
import de.iotacb.cloud.utilities.render.Render;

public class Particle extends Entity {

	public Particle(Window window) {
		super(window);
	}
	
	VectorD vel;

	@Override
	public void initialize() {
		this.vel = new VectorD().randomize(-2, 2, -2, 2);
		location.randomize(10, window.windowWidth - 10, 10, window.windowHeight - 10);
	}

	@Override
	public void update() {
		location.add(vel);
		if (location.x > window.windowWidth - 5 || location.x < 5) {
			vel.x *= -1;
		}
		if (location.y > window.windowHeight - 5 || location.y < 5) {
			vel.y *= -1;
		}
	}

	@Override
	public void draw() {
		Render.startSmooth();
		Render.polygonCentered(location.getVectorInt(), 10, 12, new Color(241, 39, 39));
		Render.endSmooth();
	}

}
