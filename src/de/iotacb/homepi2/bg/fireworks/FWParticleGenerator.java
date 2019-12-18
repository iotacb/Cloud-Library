package de.iotacb.homepi2.bg.fireworks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Randoms;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.homepi2.bg.Background;

public class FWParticleGenerator extends Background {

	List<FWParticle> particles;
	FWParticle firework;
	Color color;

	Window window;

	Vec gravity;

	public FWParticleGenerator(Window window) {
		this.firework = new FWParticle(window, new Vec(Randoms.randomInteger((int)window.getWindowWidth()), window.getWindowHeight()));
		this.particles = new ArrayList<FWParticle>();
		this.gravity = new Vec(0, .3);
		this.window = window;
	}

	@Override
	public void draw() {
		if (firework != null) {
			firework.draw();
		}

		for (int i = particles.size() - 1; i >= 0; i--) {
			FWParticle p = particles.get(i);
			p.draw();
		}
	}

	@Override
	public void update() {
		if (firework != null) {
			firework.applyForce(gravity);
			firework.update();

			if (firework.explode()) {
				for (int i = 0; i < 20; i++) {
					particles.add(new FWParticle(window, firework.location, firework.color, true));
				}
				firework = null;
			}
		}

		for (int i = particles.size() - 1; i >= 0; i--) {
			FWParticle p = particles.get(i);
			p.applyForce(gravity);
			p.update();
			if (p.isDead()) {
				particles.remove(i);
			}
		}

		if (firework == null) {
			firework = new FWParticle(window, new Vec(Randoms.randomInteger((int) window.getWindowWidth()), window.getWindowHeight()));
		}
	}

	boolean done() {
		return (firework == null && particles.isEmpty());
	}

	boolean dead() {
		return particles.isEmpty();
	}

}
