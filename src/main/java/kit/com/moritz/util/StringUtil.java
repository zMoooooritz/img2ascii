package kit.com.moritz.util;

import org.jetbrains.annotations.NotNull;

/**
 * This class provides some useful operations on strings
 */
public class StringUtil {

	/**
	 * This Method takes a text and returns all lines of it that contain the given String
	 *
	 * @param text The Text to scan
	 * @param pattern The String to search for in the text
	 * @return The lines that contain the String
	 */
	@NotNull
	public static String grepLines(String text, String pattern) {
		StringBuilder content = new StringBuilder();
		String[] lines = text.split(System.getProperty("line.separator"));

		for (String line : lines) {
			if (line.contains(pattern)) {
				content.append(line + System.getProperty("line.separator"));
			}
		}
		return content.toString();
	}

	/**
	 * This Method takes a text and returns the first line
	 *
	 * @param text The Text to compute
	 * @return The first line of the text
	 */
	@NotNull
	public static String grepFirstLine(String text) {
		return text.split(System.getProperty("line.separator"))[0];
	}

	/**
	 * This Method takes a text and returns the last line
	 *
	 * @param text The Text to compute
	 * @return The last line of the text
	 */
	@NotNull
	public static String grepLastLine(String text) {
		String[] lines = text.split(System.getProperty("line.separator"));
		return lines[lines.length - 1];
	}

	/**
	 * This Method takes a text and returns the number of appearances of the String in the text
	 *
	 * @param text The Text to scan
	 * @param pattern The String to search for in the text
	 * @return The number of appearances
	 */
	public static int wordCount(String text, String pattern) {
		int lastIndex = 0;
		int count = 0;

		while (lastIndex != -1) {
			lastIndex = text.indexOf(pattern, lastIndex);

			if(lastIndex != -1){
				count++;
				lastIndex += pattern.length();
			}
		}
		return count;
	}

	/**
	 * This Method takes a text and returns the number of lines that contain the String
	 *
	 * @param text The Text to scan
	 * @param pattern The String to search for in the text
	 * @return The number of lines that contain the String
	 */
	public static int lineCount(String text, String pattern) {
		String lines[] = text.split(System.getProperty("line.separator"));
		int count = 0;

		for (String line : lines) {
			if (line.contains(pattern)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * This method takes a int matrix and converts it into a string that is supposed to be an ascii-art
	 *
	 * @param imageMatrix the int matrix to convert
	 * @return the resulting String (ascii-art)
	 */
	public static String matrixToString(int[][] imageMatrix) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < imageMatrix[0].length; i++) {
			for (int j = 0; j < imageMatrix.length; j++) {
				int brightness = imageMatrix[j][i];
				if (brightness >= 200)
					stringBuilder.append(" ");
				else if (brightness >= 150)
					stringBuilder.append("'");
				else if (brightness >= 100)
					stringBuilder.append("*");
				else if (brightness >= 50)
					stringBuilder.append("+");
				else
					stringBuilder.append("@");
			}
			stringBuilder.append(System.lineSeparator());
		}

		Logger.info("Successfully converted the int matrix into a String (ascii-art)");

		return stringBuilder.toString();
	}

}
