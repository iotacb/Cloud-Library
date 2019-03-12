# Creating the window
To create a Window, you have to create a Main class.
In the main class create a initialized display variable inside of the main method.
```java
public class Main {

	public static void main(String[] args) throws CloudInitializeException, CloudCreateException {
		Display display = new Display(new VectorI(600, 600), "Creating the window", false, true); // Creates the window
	}

}
```
Now you have to make a new class and extend it with the screen class and add the unimplemented methods.

```java
public class RenderingScreen extends Screen {

	public RenderingScreen(Display display) {
		super(display);
	}

	@Override
	public void draw() {
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

}
```

Now go back into the main class and add your created screen class to the display and then update the display.

```java
public class Main {

	public static void main(String[] args) throws CloudInitializeException, CloudCreateException {
		Display display = new Display(new VectorI(600, 600), "Creating the window", false, true); // Creates the Window
		
		display.setMainScreen(RenderingScreen.class); // Set the screen
		
		display.update(); // Update the window
	}

}
```

Thats it, now you can run the application and look onto your empty window!
You now can go to the render setup [here](https://github.com/iotacb/Cloud-Library/blob/master/tutorials/Rendering%20a%20circle/circle.md)!

