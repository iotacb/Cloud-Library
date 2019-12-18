package de.iotacb.cloud.core;

import de.iotacb.cloud.core.info.Info;

public class Main {

	public static void main(String[] args) {
		System.out.println(
				String.format("%s library version %s created by %s\nVisit the official website %s for updates!",
						Info.LIBRARY_NAME, Info.LIBRARY_VERSION, Info.LIBRARY_AUTHOR, Info.LIBRARY_WEBSITE));
	}

}
