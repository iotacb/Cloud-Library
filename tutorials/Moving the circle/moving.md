# User input
First, create a new class which extends the entity class. The entity class is the class that should be used for entities. Then go back into your screen class, and if you have to, delete the rendering stuff from the last part. Then create a new variable of your entity class and initialize it in the `init` method. Then add the render and update method of the entity class to the methods of your screen class.
```java
public class RenderingScreen extends Screen {
	
	Ball ball;

	public RenderingScreen(Display display) {
		super(display);
	}

	@Override
	public void init() {
		ball = new Ball(display);
	}
	
	@Override
	public void draw() {
		ball.draw();
	}

	@Override
	public void update() {
		ball.update();
	}

}
```

Then go back to the entity class and add a circle like you did in the last part, just use as the location the location vector. I wan't to have the circle at the start of the application in the center, so I set the location vector to the center of the screen.

```java
	public Ball(Display display) {
		super(display);
	}

	@Override
	public void init() {
		location.set(screenDimensions.getCenter()); // Set the location to the center of the screen
	}
	
	@Override
	public void draw() {
		Render.color(Color.orange); // Set the rendering color
		Render.drawCircle(location, 60); // Render a circle at the current location with a radius of 60
	}

	@Override
	public void update() {
	}

}
```

Now go into the `update` method and add the movement part.

```java
public class Ball extends Entity {

	public Ball(Display display) {
		super(display);
	}

	@Override
	public void init() {
		location.set(screenDimensions.getCenter()); // Set the location to the center of the screen
	}
	
	@Override
	public void draw() {
		Render.color(Color.orange); // Set the rendering color
		Render.drawCircle(location, 60); // Render a circle at the current location with a radius of 60
	}

	@Override
	public void update() {
		moveEntityWithKeys(Keys.W, Keys.A, Keys.S, Keys.D, 4); // Move the entity with the WASD keys
		location.constrain(60, screenDimensions.x - 60, 60, screenDimensions.y - 60); // Lock the entity inside the window
	}

}
```

I added there an another line `location.constrain` to lock the entity inside of the window.

And thats it, you can now go to the [next part]()!
