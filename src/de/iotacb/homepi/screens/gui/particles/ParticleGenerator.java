package de.iotacb.homepi.screens.gui.particles;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.files.FilesReader;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.render.Render;

public class ParticleGenerator {
	
	public List<Particle> particles;
	
	public ParticleGenerator(Window window) {
		this.particles = new ArrayList<Particle>();
		int amount = Integer.valueOf(FilesReader.readAsString(new File("particles.txt")).trim());
		for (int i = 0; i < amount; i++) {
			particles.add(new Particle(window));
		}
	}
	
	public void draw() {
		for (Particle p1 : particles) {
			for (Particle p2 : particles) {
				double dist = Maths.dist(p1.location, p2.location);
				if (dist < 150) {
					Render.line(p1.location.getVectorInt(), p2.location.getVectorInt(), Colors.setAlpha(Color.white, (int) Maths.clamp(150 - dist, 0, 255)));
				}
			}
			p1.draw();
		}
	}
	
	public void update() {
		particles.forEach(Particle::update);
	}

}
