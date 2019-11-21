# How to show a window?
First create a Start class [e.g: Main.class] and create a variable of the class Window of Cloud and set the parameters.
"new Window(windowWidth, windowHeight, windowTitle, windowResizable, windowFullscreen)".

```java
	public static void main(String...strings) throws Exception {
		Window window = new Window(600, 600, "Project", false, true);
	}

```

Now the Window is saved in the variable.
To show it just call the show() method of the Window like this.

```java
	public static void main(String...strings) throws Exception {
		Window window = new Window(600, 600, "Project", false, true);
		window.show();
	}

```

That's it, there are some other stuff you can do with the window but that i'll explain in a another tutorial.
