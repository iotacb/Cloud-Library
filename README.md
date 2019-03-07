


# What is Cloud?
Cloud is a simple 2D game engine written in Java. It's very easy to use and can be learned fast.
It's not an engine to create quality games, and it's not ment to be. It's just a little fun project for me!

# Are there demos?
Currently not, but I am working on some demos!

# What libraries are used?
Cloud uses [LWJGL](https://www.lwjgl.org/) for rendering and some other stuff.
For sound it uses [Jaco](http://jacomp3player.sourceforge.net/)

# Installing the Engine
First you have to download the library from the [release page](https://github.com/iotacb/Cloud-Engine/releases).

When you downloaded the library you have to add it to your project.
For Eclipse:
```Rightclick on your project → Build Path → Configure Build Path... → Libraries → Add External JARs...```

For IntelliJ-IDEA:
```File → Project Structure... → Modules → Dependencies → Click '+'```

# Getting started
After you have installed the library you can start by creating the 'Display'.

```java
public static void main(String[] args) throws CloudInitializeException, CloudCreateException {
	Display display = new Display(new VectorI(), "Demo", false, true);
}
```
Create a new class which extends the Screen class. In this class we will draw the game and update everything.
Then go back to your main method and add the screen to the display,
then update the display.

```java
public static void main(String[] args) throws CloudInitializeException, CloudCreateException {
	Display display = new Display(new VectorI(), "Demo", false, true); // Window
	display.setMainScreen(GameScreen.class); // Set the main rendering screen
	display.update(); // update the display
}
```

# Handling and drawing a Entity
Create a new class which extends the Entity class.
Make a Variable of your class in the screen class, initialize the entity in the init method of the screen class. Add the entity render and update method to the one of the screen class.

```java
Player player;

@Override
public void draw() {
	player.draw();
}

@Override
public void init() {
	player = new Player(display);
}

@Override
public void update() {
	player.update();
}
```

go back to your entity class.
I'm going to make the player a circle, so ill render it in the render method, and I wan't to move it with WASD so I add the moveEntity method to the update method.

```java
public Player(Display display) {
	super(display);
}

@Override
public void draw() {
	Render.color(Color.cyan); // Set the color to cyan
	Render.drawCircle(location.makeInteger(), 20); // Render a circle with a radius of 20 at the location
}

@Override
public void init() {
	location.set(screenDimensions.x / 2, screenDimensions.y / 2); // Spawn the player in the window center
}

@Override
public void update() {
	moveEntityWithKeys(Keys.W, Keys.A, Keys.S, Keys.D, .2); // Move the player with keys
	location.constrain(0, screenDimensions.x, 0, screenDimensions.y); // Lock the player inside the Window
}
```
