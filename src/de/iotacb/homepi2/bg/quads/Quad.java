package de.iotacb.homepi2.bg.quads;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Render;

public class Quad extends Entity {
	
	Vec size;
	int speed;

	public Quad(Window window, Vec size, Vec location, int speed) {
		super(window);
		
		this.size = size;
		this.location = location;
		this.speed = speed;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void update() {
	}

	@Override
	public void draw() {
		Render.rect(location, size, Colors.setAlpha(Colors.rainbow(1000 + speed), 40));
	}

}
