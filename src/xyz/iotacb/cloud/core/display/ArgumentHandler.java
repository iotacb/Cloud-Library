package xyz.iotacb.cloud.core.display;

import java.util.regex.Pattern;

import xyz.iotacb.cloud.core.exceptions.CloudFalseArgumentException;
import xyz.iotacb.cloud.utilities.math.Vector;

public class ArgumentHandler {
	
	String[] arguments;
	
	public Vector displayDimensions;
	public int frameLock;
	public String title;
	public boolean useArguments = false, resizeable, fullscreen;
	
	public ArgumentHandler(final String...arguments) {
		this.arguments = arguments;
		try {
			handleArguments();
		} catch (CloudFalseArgumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Handle the arguments
	 * @throws CloudFalseArgumentException
	 */
	void handleArguments() throws CloudFalseArgumentException {
		this.useArguments = this.arguments.length > 4;
		if (this.useArguments) {
			this.handleDimensions();
			this.handleTitle();
			this.handleFrameLock();
			this.handleResizeable();
			this.handleFullscreen();
		} else {
			System.out.println("Run arguments are not used, using hardcoded arguments...");
		}
	}
	
	/**
	 * Handles the argument for the window dimensions
	 * @throws CloudFalseArgumentException
	 */
	void handleDimensions() throws CloudFalseArgumentException {
		String dimensionArgument = this.arguments[0];
		if (Pattern.matches("-displayDimensions:[0-9]+x[0-9]+", dimensionArgument)) {
			String values = dimensionArgument.split(":")[1];
			int xDimensions = Integer.valueOf(values.split("x")[0]);
			int yDimensions = Integer.valueOf(values.split("x")[1]);
			this.displayDimensions = Vector.createVector(xDimensions, yDimensions);
		} else {
			throw new CloudFalseArgumentException(String.format("%s is a false dimension argument!", dimensionArgument));
		}
	}
	
	/**
	 * Handles the argument for the window title
	 * @throws CloudFalseArgumentException
	 */
	void handleTitle() throws CloudFalseArgumentException {
		String titleArgument = this.arguments[1];
		if (Pattern.matches("-title:[a-zA-Z\\ \\,\\;\\.\\:\\-\\_\\#\\'\\+\\*\\?\\ß]+", titleArgument)) {
			String title = titleArgument.split(":")[1];
			this.title = title;
		} else {
			throw new CloudFalseArgumentException(String.format("%s is a false title argument!", titleArgument));
		}
	}
	
	/**
	 * Handles the argument for max framerate
	 * @throws CloudFalseArgumentException
	 */
	void handleFrameLock() throws CloudFalseArgumentException {
		String frameLockArgument = this.arguments[2];
		if (Pattern.matches("-frameLock:[0-9]+", frameLockArgument)) {
			int frameLock = Integer.valueOf(frameLockArgument.split(":")[1]);
			this.frameLock = frameLock;
		} else {
			throw new CloudFalseArgumentException(String.format("%s is a false frame lock argument!", frameLockArgument));
		}
	}
	
	/**
	 * Handles the argument for window resizeableisation
	 * @throws CloudFalseArgumentException
	 */
	void handleResizeable() throws CloudFalseArgumentException {
		String resizeableArgument = this.arguments[3];
		if (Pattern.matches("-resizeable:(true|false)", resizeableArgument)) {
			boolean resizeable = Boolean.valueOf(resizeableArgument.split(":")[1]);
			this.resizeable = resizeable;
		} else {
			throw new CloudFalseArgumentException(String.format("%s is a false resizeable argument!", resizeableArgument));
		}
	}
	
	/**
	 * Handles the argument for fullscreen mode
	 * @throws CloudFalseArgumentException
	 */
	void handleFullscreen() throws CloudFalseArgumentException {
		String fullscreenArgument = this.arguments[4];
		if (Pattern.matches("-fullscreen:(true|false)", fullscreenArgument)) {
			boolean fullscreen = Boolean.valueOf(fullscreenArgument.split(":")[1]);
			this.fullscreen = fullscreen;
		} else {
			throw new CloudFalseArgumentException(String.format("%s is a false fullscreen argument!", fullscreenArgument));
		}
	}
	
}
 