# Basic rendering
In this part I'll show you how to render a circle in your screen.
First you have to go into you screen class, and then into the `draw` method.
Now you can set the rendering color and render a circle.
```java
public class RenderingScreen extends Screen {

	public RenderingScreen(Display display) {
		super(display);
	}

	@Override
	public void init() {
	}
	
	@Override
	public void draw() {
		Render.color(Color.orange); // Set the rendering color
		Render.drawCircle(screenDimensions.getCenter(), 60); // Draw a circle in the center of the screen with a radius of 60
	}

	@Override
	public void update() {
	}

}
```
And thats how to draw a circle!
In the next part I'll show you how to move the circle, click [here](https://github.com/iotacb/Cloud-Library/blob/master/tutorials/The%20first%20window/display.md)!
