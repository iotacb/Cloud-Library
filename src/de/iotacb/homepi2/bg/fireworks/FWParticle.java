package de.iotacb.homepi2.bg.fireworks;

import java.awt.Color;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.math.Randoms;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Render;

public class FWParticle extends Entity {

	Vec velocity, acceleration;

	int lifespan;
	
	double lifetime, speed;

	boolean small, seed, cycle;
	
	Color color;

	public FWParticle(Window window, Vec location) {
		super(window);
		this.velocity = new Vec(Randoms.randomDouble(-5, 5), Randoms.randomDouble(-20, -15));
		this.acceleration = new Vec();
		this.lifespan = 255;
		this.small = false;
		this.location.set(location.clone());
		this.seed = true;
		this.color = Colors.random();
	}

	public FWParticle(Window window, Vec location, Color color, boolean small) {
		super(window);
		double rand_num = Randoms.randomDouble(.5, 8);
		this.color = color;
		this.velocity = Vec.random();
		this.velocity.mul(rand_num, rand_num, rand_num);
		this.acceleration = new Vec();
		this.lifespan = 255;
		this.location.set(location.clone());
		this.small = small;
		this.lifetime = Randoms.randomDouble(255);
		this.cycle = Randoms.randomBoolean();
		this.speed = Randoms.randomDouble(.1, .2);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void update() {
		velocity.add(acceleration);
		location.add(velocity);
		
		if (!seed) {
			lifetime += speed;
			lifespan -= 3;
			velocity.mul(.97, .97, .97);
			location.add(new Vec(Math.cos(cycle ? lifetime : -lifetime), Math.sin(cycle ? lifetime : -lifetime)).mul(.5, .5, .5));
		}
		acceleration.mul(0, 0, 0);
	}

	@Override
	public void draw() {
		Render.circle(location, seed ? 4 : 2, Colors.setAlpha(color, lifespan));
	}
	
	void applyForce(Vec force) {
		acceleration.add(force);
	}
	
	boolean explode() {
		if (seed && velocity.y > 0) {
			lifespan = 0;
			return true;
		}
		return false;
	}
	
	boolean isDead() {
		return lifespan <= 0;
	}

}
