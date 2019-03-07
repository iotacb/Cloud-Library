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
