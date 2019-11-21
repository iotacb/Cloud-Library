package de.iotacb.cloud.utilities.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesReader {

	public static StringBuilder readAsStringBuilder(File file, int fromLine, int toLine) {
		StringBuilder fileContents = new StringBuilder();
		try {

			BufferedReader reader = new BufferedReader(new FileReader(file));

			String currentReadingLine;
			int readLines = 0;

			while ((currentReadingLine = reader.readLine()) != null) {
				readLines++;
				if ((toLine > 0 ? readLines > toLine : false) || readLines < fromLine)
					continue;
				fileContents.append(currentReadingLine).append("\n");
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContents;
	}

	public static StringBuilder readAsStringBuilder(String file, int fromLine, int toLine) {
		return readAsStringBuilder(new File(file), fromLine, toLine);
	}

	public static StringBuilder readAsStringBuilder(File file) {
		return readAsStringBuilder(file, 0, 0);
	}

	public static StringBuilder readAsStringBuilder(String file) {
		return readAsStringBuilder(new File(file));
	}

	public static String readAsString(File file, int fromLine, int toLine) {
		return readAsStringBuilder(file, fromLine, toLine).toString();
	}

	public static String readAsString(String file, int fromLine, int toLine) {
		return readAsString(new File(file), fromLine, toLine);
	}

	public static String readAsString(File file) {
		return readAsString(file, 0, 0);
	}

	public static String readAsString(String file) {
		return readAsString(new File(file));
	}

	public static List<String> readAsStringList(File file, int fromLine, int toLine) {
		List<String> fileContent = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(file));

			String currentReadingLine;
			int readLines = 0;

			while ((currentReadingLine = reader.readLine()) != null) {
				readLines++;
				if ((toLine > 0 ? readLines > toLine : false) || readLines < fromLine)
					continue;
				fileContent.add(currentReadingLine);
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileContent;
	}

	public static List<String> readAsStringList(String file, int fromLine, int toLine) {
		return readAsStringList(new File(file), fromLine, toLine);
	}

	public static List<String> readAsStringList(File file) {
		return readAsStringList(file, 0, 0);
	}

	public static List<String> readAsStringList(String file) {
		return readAsStringList(new File(file));
	}

	public static String[] readAsStringArray(File file, int fromLine, int toLine) {
		List<String> list = readAsStringList(file, fromLine, toLine);
		return list.toArray(new String[list.size()]);
	}

	public static String[] readAsStringArray(String file, int fromLine, int toLine) {
		return readAsStringArray(new File(file), fromLine, toLine);
	}

	public static String[] readAsStringArray(File file) {
		return readAsStringArray(file, 0, 0);
	}

	public static String[] readAsStringArray(String file) {
		return readAsStringArray(new File(file));
	}

	public static byte[] readAsByteArray(File file, int fromLine, int toLine) {
		return readAsString(file, fromLine, toLine).getBytes();
	}

	public static byte[] readAsByteArray(String file, int fromLine, int toLine) {
		return readAsByteArray(new File(file), fromLine, toLine);
	}

	public static byte[] readAsByteArray(File file) {
		return readAsByteArray(file, 0, 0);
	}

	public static byte[] readAsByteArray(String file) {
		return readAsByteArray(new File(file));
	}

}
