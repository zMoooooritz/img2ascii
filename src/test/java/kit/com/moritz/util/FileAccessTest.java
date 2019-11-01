package kit.com.moritz.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileAccessTest {

	public static final String fileName = "testFile.txt";

	@BeforeAll
	public static void setUp() {

	}

	@BeforeEach
	public void initFileTest() {
		FileAccess.createFile(fileName);
		FileAccess.clearFile(fileName);
	}

	@Test
	public void createFileTest() {
		assertFalse(FileAccess.createFile(fileName));
	}

	@Test
	public void deleteFileTest() {
		assertTrue(FileAccess.deleteFile(fileName));
		assertFalse(FileAccess.deleteFile(fileName));
	}

	@Test
	public void appendTextTest() {
		assertTrue(FileAccess.appendText(fileName, "Das ist meine Zeile1"));
		assertTrue(FileAccess.appendText(fileName, "Das ist die Mitte"));
		assertTrue(FileAccess.appendText(fileName, "Das ist meine Zeile3"));
	}

	@Test
	public void readContentTest() {
		assertTrue(FileAccess.appendText(fileName, "Das ist ein Test!"));
		assertEquals("Das ist ein Test!" + System.getProperty("line.separator"), FileAccess.readContent(fileName));
	}

	@Test
	public void readFirstLineTest() {
		appendTextTest();
		assertEquals("Das ist meine Zeile1", FileAccess.readFirstLine(fileName));
	}

	@Test
	public void readLastLineTest() {
		appendTextTest();
		assertEquals("Das ist meine Zeile3", FileAccess.readLastLine(fileName));
	}

	@Test
	public void clearFileTest() {
		appendTextTest();
		String content = FileAccess.readContent(fileName);
		assertTrue(FileAccess.clearFile(fileName));
		appendTextTest();
		assertEquals(content, FileAccess.readContent(fileName));
	}

	@AfterAll
	public static void tearDown() {
		FileAccess.deleteFile(fileName);
	}

}
