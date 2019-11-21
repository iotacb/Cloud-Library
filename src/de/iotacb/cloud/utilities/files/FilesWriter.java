package de.iotacb.cloud.utilities.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilesWriter {

	public static void writeString(File file, String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			String[] lines = content.split("\n");

			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeString(String file, String content) {
		writeString(new File(file), content);
	}

	public static void writeStringBuilder(File file, StringBuilder content) {
		writeString(file, content.toString());
	}

	public static void writeStringBuilder(String file, StringBuilder content) {
		writeStringBuilder(new File(file), content);
	}

	public static void writeStringList(File file, List<String> content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			for (String line : content) {
				writer.write(line);
				writer.newLine();
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void writeStringList(String file, List<String> content) {
		writeStringList(new File(file), content);
	}

	public static void writeStringArray(File file, String[] content) {
		writeStringList(file, Arrays.asList(content));
	}

	public static void writeStringArray(String file, String[] content) {
		writeStringArray(new File(file), content);
	}

	public static void appendString(File file, String content, int line) {
		if (!file.exists())
			return;

		List<String> fileContent = FilesReader.readAsStringList(file);
		List<String> contentAsList = Arrays.asList(content.split("\n"));
		boolean add = false;

		if (line < 0) {
			fileContent.addAll(contentAsList);
		} else {
			for (int i = 0; i < fileContent.size(); i++) {
				if (i == line) {
					add = true;
				}
				if (add) {
					Collections.reverse(contentAsList);
					for (String sLine : contentAsList) {
						fileContent.add(i, sLine);
					}
					add = false;
				}
			}
		}

		writeStringList(file, fileContent);
	}


	public static void appendString(String file, String content, int line) {
		appendString(new File(file), content, line);
	}

	public static void appendStringBuilder(File file, StringBuilder content, int line) {
		appendString(file, content.toString(), line);
	}

	public static void appendStringBuilder(String file, StringBuilder content, int line) {
		appendString(new File(file), content.toString(), line);
	}

	public static void appendStringList(File file, List<String> content, int line) {
		if (!file.exists())
			return;

		List<String> fileContent = FilesReader.readAsStringList(file);
		boolean add = false;

		if (line < 0) {
			fileContent.addAll(content);
		} else {
			for (int i = 0; i < fileContent.size(); i++) {
				if (i == line) {
					add = true;
				}
				if (add) {
					Collections.reverse(content);
					for (String sLine : content) {
						fileContent.add(i, sLine);
					}
					add = false;
				}
			}
		}

		writeStringList(file, fileContent);
	}

	public static void appendStringList(String file, List<String> content, int line) {
		appendStringList(new File(file), content, line);
	}

	public static void appendStringArray(File file, String[] content, int line) {
		appendStringList(file, Arrays.asList(content), line);
	}

	public static void appendStringArray(String file, String[] content, int line) {
		appendStringArray(new File(file), content, line);
	}

	public static void mergeFiles(File file, File upperFile, File lowerFile) {
		List<String> upperFileContent = FilesReader.readAsStringList(upperFile),
				lowerFileContent = FilesReader.readAsStringList(lowerFile);
		upperFileContent.addAll(lowerFileContent);
		writeStringList(file, upperFileContent);
	}


	public static void mergeFiles(String file, String upperFile, String lowerFile) {
		mergeFiles(new File(upperFile), new File(lowerFile), new File(file));
	}

	public static void splitFile(File file, File filefileOne, File filefileTwo, int line) {
		if (!file.exists() || line < 0)
			return;

		List<String> content = FilesReader.readAsStringList(file);
		List<String> contentFileOne = new ArrayList<>();
		List<String> contentFileTwo = new ArrayList<>();

		boolean add = false;

		for (int i = 0; i < content.size(); i++) {
			if (i == line) {
				add = true;
			}
			if (add) {
				contentFileTwo.add(content.get(i));
			} else {
				contentFileOne.add(content.get(i));
			}
		}

		writeStringList(filefileOne, contentFileOne);
		writeStringList(filefileTwo, contentFileTwo);
	}

	public static void splitFile(String file, String filefileOne, String filefileTwo, int line) {
		splitFile(new File(file), new File(filefileOne), new File(filefileTwo), line);
	}

	public static void removeContent(File file, int fromLine, int toLine) {
		if (!file.exists())
			return;

		List<String> fileContent = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(file));

			String currentReadingLine;
			int readLines = 0;

			while ((currentReadingLine = reader.readLine()) != null) {
				readLines++;
				if (readLines >= fromLine && (toLine <= 0 ? false : readLines <= toLine))
					continue;
				fileContent.add(currentReadingLine);
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		writeStringList(file, fileContent);

	}

	public static void removeContent(String file, int fromLine, int toLine) {
		removeContent(new File(file), fromLine, toLine);
	}

	public static void writeBytes(File file, byte[] bytes) {
		writeString(file, new String(bytes));
	}

	public static void writeBytes(String file, byte[] bytes) {
		writeBytes(new File(file), bytes);
	}

}
