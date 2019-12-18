package de.iotacb.homepi2.bg.particles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.homepi2.bg.Background;

public class ParticleGenerator extends Background {
	
	List<Particle> particles;
	
	public ParticleGenerator(Window window) {
		super();
		this.particles = new ArrayList<Particle>();
		
		for (int i = 0; i < 30; i++) {
			particles.add(new Particle(window));
		}
	}

	@Override
	public void draw() {
		for (Particle p1 : particles) {
			Render.start();
			for (Particle p2 : particles) {
				double dist = Maths.dist(p1.location, p2.location);
				if (dist < 255) {
					Render.line(p1.location, p2.location, Colors.setAlpha(Color.white, (int) Maths.clamp(255 - dist, 0, 255)));
				}
			}
			p1.draw();
		}
	}
	
	@Override
	public void update() {
		particles.forEach(Particle::update);
	}

}
