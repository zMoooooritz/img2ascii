package kit.com.moritz.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {

	String text = "Das ist meine Zeile1" + System.getProperty("line.separator")
			+ "Das ist die Mitte" + System.getProperty("line.separator")
			+ "Das ist meine Zeile3" + System.getProperty("line.separator");

	String grepZeile = "Das ist meine Zeile1" + System.getProperty("line.separator")
			+ "Das ist meine Zeile3" + System.getProperty("line.separator");

	String grepMitte = "Das ist die Mitte" + System.getProperty("line.separator");

	String ersteZeile = "Das ist meine Zeile1";

	String letzteZeile = "Das ist meine Zeile3";

	@Test
	public void grepLinesTest() {
		assertEquals(grepZeile, StringUtil.grepLines(text, "Zeile"));
		assertEquals(grepMitte, StringUtil.grepLines(text, "Mitte"));
	}

	@Test
	public void grepFirstLineTest() {
		assertEquals(ersteZeile, StringUtil.grepFirstLine(text));
		assertEquals(ersteZeile, StringUtil.grepFirstLine(grepZeile));
		assertEquals(ersteZeile, StringUtil.grepFirstLine(ersteZeile));
	}

	@Test
	public void grepLastLineTest() {
		assertEquals(letzteZeile, StringUtil.grepLastLine(text));
		assertEquals(letzteZeile, StringUtil.grepLastLine(grepZeile));
		assertEquals(letzteZeile, StringUtil.grepLastLine(letzteZeile));
	}

	@Test
	public void wordCountTest() {
		assertEquals(2, StringUtil.wordCount(text, "Zeile"));
		assertEquals(1, StringUtil.wordCount(text, "Mitte"));
		assertEquals(9, StringUtil.wordCount(text, "i"));
	}

	@Test
	public void lineCountTest() {
		assertEquals(2, StringUtil.lineCount(text, "Zeile"));
		assertEquals(1, StringUtil.lineCount(text, "Mitte"));
		assertEquals(3, StringUtil.lineCount(text, "i"));
	}

	@Test
	public void matrixToStringTest() {
		int[][] imageMatrix = {{0, 255, 0}, {255, 0, 255}, {0, 255, 0}};

		assertEquals("@ @" + System.lineSeparator() + " @ " + System.lineSeparator() + "@ @" + System.lineSeparator(),
				StringUtil.matrixToString(imageMatrix));
	}

}
