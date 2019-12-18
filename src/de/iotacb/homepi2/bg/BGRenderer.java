package de.iotacb.homepi2.bg;

import java.util.ArrayList;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Randoms;
import de.iotacb.homepi2.bg.fireworks.FWParticleGenerator;
import de.iotacb.homepi2.bg.particles.ParticleGenerator;
import de.iotacb.homepi2.bg.quads.QuadGenerator;

public class BGRenderer {
	
	int bg_mode;
	
	ArrayList<Background> bgs;
	
	public BGRenderer(Window window) {
		this.bgs = new ArrayList<Background>();
		
		bgs.add(new ParticleGenerator(window));
//		bgs.add(new QuadGenerator(window));
//		bgs.add(new FWParticleGenerator(window));
		
		bg_mode = Randoms.randomInteger(0, bgs.size() - 1);
	}
	
	public void draw() {
		this.bgs.get(bg_mode).draw();
	}
	
	public void update() {
		this.bgs.get(bg_mode).update();
	}

}
