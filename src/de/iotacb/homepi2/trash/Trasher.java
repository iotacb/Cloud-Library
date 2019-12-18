package de.iotacb.homepi2.trash;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.files.FilesReader;
import de.iotacb.cloud.utilities.time.Timer;

public class Trasher {
	
	Window window;
	
	GpioController gpio;
	
	GpioPinDigitalOutput speaker;
	
	HashMap<String, String> days;
	
	Timer trashUpdateTimer;
	
	public Trasher(Window window) {
		this.window = window;
		this.gpio = window.getGpioController();
		init();
	}
	
	void init() {
		this.speaker = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Speaker", PinState.LOW);
		
		this.speaker.setShutdownOptions(true, PinState.LOW);
		
		this.days = new HashMap<String, String>();
		load();
		
		this.trashUpdateTimer = new Timer();
	}
	
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (trashUpdateTimer.havePassed(10)) {
						Date date = new Date();
						String format = new SimpleDateFormat("dd.MM").format(date).toString();
						//days.containsKey(format)
						if (true) {
							for (int i = 0; i < 1000; i++) {
								speaker.high();
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								speaker.low();
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}, "TrashUpdater").start();
	}
	
	public void kill() {
		gpio.shutdown();
		gpio.unprovisionPin(speaker);
	}
	
	void load() {
		List<String> days = FilesReader.readAsStringList("homepi/files/trash.txt");
		for (String day : days) {
			day = day.trim();
			this.days.put(day.split(":")[0], day.split(":")[1]);
		}
	}
	
}
