package de.iotacb.homepi.screens.main;

import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.lwjgl.glfw.GLFW;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.window.World;
import de.iotacb.cloud.utilities.collision.CBox;
import de.iotacb.cloud.utilities.collision.CLine;
import de.iotacb.cloud.utilities.collision.Collision;
import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.files.FilesReader;
import de.iotacb.cloud.utilities.files.FilesWriter;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.cloud.utilities.render.Image;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.homepi.screens.gui.buttons.ExitButton;
import de.iotacb.homepi.screens.gui.buttons.ScreenChangeButton;
import de.iotacb.homepi.screens.info.InfoScreen;
import de.iotacb.homepi.screens.muell.MuellScreen;
import de.iotacb.homepi.screens.shopping.ShoppingScreen;

public class MainScreen extends World {

	Text logo, text;

	Image img;

	GpioController gpio;

	GpioPinDigitalOutput led;
	
	d d;

	public MainScreen(Window window) {
		super(window);
		if (!System.getProperty("os.name").contains("Windows")) {
			if (FilesReader.readAsString(new File("gpio.txt")).trim().equalsIgnoreCase("true")) {
				gpio = GpioFactory.getInstance();
				led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "LED", PinState.HIGH);
				led.setShutdownOptions(true, PinState.LOW);
				FilesWriter.writeString(new File("gpio.txt"), "false");
			}
		}
	}

	@Override
	public void initialize() {
		this.logo = new Text(60);
		this.text = new Text(10);
		this.d = new d(window);

		this.img = new Image(window, new File("assets/textures/homepi-logo.png"));

		addEntity(new ScreenChangeButton(window, windowWidth / 2 - 415, windowHeight / 2 + 80, "Muell",
				MuellScreen.class));
		addEntity(new ScreenChangeButton(window, windowWidth / 2 - 205, windowHeight / 2 + 80, "Infos",
				InfoScreen.class));
		addEntity(new ScreenChangeButton(window, windowWidth / 2 + 5, windowHeight / 2 + 80, "Einkaufsliste",
				ShoppingScreen.class));
		addEntity(new ExitButton(window, windowWidth / 2 + 215, windowHeight / 2 + 80, "Exit "));
		addEntity(d);
	}

	public void exitPi() {
		gpio.shutdown();
		System.exit(0);
	}

	@Override
	public void update() {
		if (GLFW.glfwWindowShouldClose(window.windowId)) {
			FilesWriter.writeString(new File("gpio.txt"), "true");
		}
		updateEntities();
		if (window.inputHandler.isKeyPressed(Keys.H)) {
			led.toggle();
		}
	}

	@Override
	public void draw() {
		drawLogo();
		drawEntities();
	}

	void drawLogo() {
		String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
		text.drawText(windowWidth / 2 - text.getHWidth(date) - 16, windowHeight / 2, date, Color.white);
		logo.drawText(windowWidth / 2 - logo.getHWidth("HomePi"), windowHeight / 2, "HomePi", Color.white);
	}

}
