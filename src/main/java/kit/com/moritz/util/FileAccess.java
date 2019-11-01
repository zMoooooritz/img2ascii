package kit.com.moritz.util;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class provides an interface to create, edit, read and delete files
 *
 * No logging in this class otherwise this would result in a endless recursion
 */
public class FileAccess {

	/**
	 * This method tries to create a new file with the given name
	 *
	 * @param fileName the name of the file
	 *
	 * @return whether the operation was successful or not
	 */
	public static boolean createFile(String fileName) {
		try {
			Files.createFile(Paths.get(fileName));
			return true;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method tries to append text to the file with the given name
	 *
	 * @param fileName the name of the file
	 * @param text the text to append to the file
	 *
	 * @return whether the operation was successful or not
	 */
	public static boolean appendText(String fileName, String text) {
		createFile(fileName);

		try (FileWriter fw = new FileWriter(fileName, true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw)) {
			out.println(text);
			return true;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method tries to clear the file with the given name
	 *
	 * @param fileName the name of the file
	 *
	 * @return whether the operation was successful or not
	 */
	public static boolean clearFile(String fileName) {
		if (createFile(fileName)) {
			return true;
		}

		try (FileWriter fw = new FileWriter(fileName);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw)) {
			out.print("");
			return true;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method tries to delete the file with the given name
	 *
	 * @param fileName the name of the file
	 *
	 * @return whether the operation was successful or not
	 */
	public static boolean deleteFile(String fileName) {
		try {
			Files.delete(Paths.get(fileName));
			return true;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method returns the first line of the given file
	 *
	 * @param fileName the name of the file
	 *
	 * @return the first line of the file
	 */
	public static String readFirstLine(String fileName) {
		String content = readContent(fileName);
		int index = content.indexOf(System.getProperty("line.separator"));
		if (index == -1)
			return content;
		return content.substring(0, index);
	}

	/**
	 * This method returns the last line of the given file
	 *
	 * @param fileName the name of the file
	 *
	 * @return the last line of the file
	 */
	public static String readLastLine(String fileName) {
		String content = readContent(fileName);
		int length = content.length();
		int index = content.substring(0, length - 2).lastIndexOf(System.getProperty("line.separator"));
		if (index == -1)
			return content;
		return content.substring(index + 1, length - 1);
	}

	/**
	 * This method returns the content of the given file as String
	 *
	 * @param fileName the name of the file
	 *
	 * @return the content of the file as String
	 */
	@NotNull
	public static String readContent(String fileName) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(fileName));
			return new String(encoded);
		} catch (IOException e) {
			//e.printStackTrace();
			return "";
		}
	}
}
