package xyz.iotacb.cloud.core.audio;

import java.io.File;

public class Audio {
	
	public File file;
	
	public Audio(final String path) {
		this.file = new File(path);
	}

}
