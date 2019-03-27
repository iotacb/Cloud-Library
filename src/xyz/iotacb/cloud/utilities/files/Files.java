package xyz.iotacb.cloud.utilities.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Files {
	
	/**
	 * This method will load the contents of a file into the returning StringBuilder
	 * @param file
	 * @return
	 */
	public StringBuilder loadFile(final File file) {
		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				contents.append(line + "\n");
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contents;
	}

}
