package xyz.iotacb.cloud.utilities.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Files {
	
	/**
	 * Load file contents
	 * @param file
	 * @return
	 */
	public StringBuilder loadFile(final File file) {
		StringBuilder contents = new StringBuilder();
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				contents.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contents;
	}

}
