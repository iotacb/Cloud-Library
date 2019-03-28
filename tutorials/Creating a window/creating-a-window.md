# How to create a window?
To create a window, create a main class, with a main method inside. In the main method we will create the display variable.
This variable can be accessed in the entire program, because it has alot of useful features inside.
```java
	public static void main(final String...strings) throws CloudInitializeException, CloudCreateException {
		Display display = new Display(new Vector3(800, 800), "Title", false, false);
	}
```

Now create a new class, this class will be the screen which will be painted onto our window.
This class has to extend the screen class of the library
```java
public class DisplayScreen extends Screen {

	public DisplayScreen(Display display) {
		super(display);
	}
	
	@Override
	public void init() {
	}

	@Override
	public void draw() {
	}

	@Override
	public void update() {
	}

}
```

Go back into the main method of your main class.
Now we can set the screen of the display and change some other settings.
```java
	public static void main(final String...strings) throws CloudInitializeException, CloudCreateException {
		Display display = new Display(new Vector3(800, 800), "Title", false, false); // Create a window 800x800 pixels of size not in fullscreen mode and not resizeable
		
		display.setScreen(DisplayScreen.class); // Set the screen of the display
		display.setFpsLock(60); // Lock the maximal fps of the application to 60
		
		display.start(); // Start the display looping and show the window
	}
```
That's it, now you can run your application and look at your created window.
In the next part you'll learn about basic rendering in cloud. Learn [here]() how.