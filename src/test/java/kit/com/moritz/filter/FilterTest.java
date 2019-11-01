package kit.com.moritz.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {

	private AbstractFilter filter;

	private BufferedImage whiteImage;
	private BufferedImage greyImage;
	private BufferedImage blackImage;

	@BeforeEach
	public void setUp() {
		whiteImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		greyImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		blackImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				whiteImage.setRGB(i, j, new Color(255, 255, 255).getRGB());
				greyImage.setRGB(i, j, new Color(127, 127, 127).getRGB());
				blackImage.setRGB(i, j, new Color(0, 0, 0).getRGB());
			}
		}
	}

	@Test
	public void standardFilterTest() {
		filter = new StandardFilter();
		filter.initValues(whiteImage, 10, 10, 0, null, 0);
		int[][] imageMatix = filter.createImageMatrix();

		assertEquals(1, imageMatix[0].length);
		assertEquals(1, imageMatix.length);
		assertEquals(255, imageMatix[0][0]);

		filter.initValues(greyImage, 1, 1, 0, null, 0);
		imageMatix = filter.createImageMatrix();

		assertEquals(10, imageMatix[0].length);
		assertEquals(10, imageMatix.length);
		assertEquals(127, imageMatix[0][0]);
	}

	@Test
	public void normalFilterTest() {
		filter = new NormalFilter();
		filter.initValues(blackImage, 10, 10, 255, null, 0);
		int[][] imageMatix = filter.createImageMatrix();

		assertEquals(1, imageMatix[0].length);
		assertEquals(1, imageMatix.length);
		assertEquals(255, imageMatix[0][0]);

		filter.initValues(greyImage, 1, 1, -100, null, 0);
		imageMatix = filter.createImageMatrix();

		assertEquals(10, imageMatix[0].length);
		assertEquals(10, imageMatix.length);
		assertEquals(27, imageMatix[0][0]);
	}

	@Test
	public void invertFilterTest() {
		filter = new InvertFilter();
		filter.initValues(blackImage, 10, 10, 0, null, 0);
		int[][] imageMatix = filter.createImageMatrix();

		assertEquals(1, imageMatix[0].length);
		assertEquals(1, imageMatix.length);
		assertEquals(255, imageMatix[0][0]);

		filter.initValues(greyImage, 1, 1, 30, null, 0);
		imageMatix = filter.createImageMatrix();

		assertEquals(10, imageMatix[0].length);
		assertEquals(10, imageMatix.length);
		assertEquals(158, imageMatix[0][0]);
	}

	@Test
	public void includeFilterTest() {
		filter = new IncludeFilter();
		filter.initValues(whiteImage, 10, 10, 33, new Color(255, 255, 254), 1);
		int[][] imageMatix = filter.createImageMatrix();

		assertEquals(1, imageMatix[0].length);
		assertEquals(1, imageMatix.length);
		assertEquals(33, imageMatix[0][0]);

		filter.initValues(greyImage, 1, 1, 30, new Color(120, 120, 120), 6);
		imageMatix = filter.createImageMatrix();

		assertEquals(10, imageMatix[0].length);
		assertEquals(10, imageMatix.length);
		assertEquals(127, imageMatix[0][0]);
	}

	@Test
	public void excludeFilterTest() {
		filter = new ExcludeFilter();
		filter.initValues(whiteImage, 10, 10, 255, new Color(255, 255, 254), 1);
		int[][] imageMatix = filter.createImageMatrix();

		assertEquals(1, imageMatix[0].length);
		assertEquals(1, imageMatix.length);
		assertEquals(255, imageMatix[0][0]);

		filter.initValues(blackImage, 1, 1, 30, new Color(120, 120, 120), 6);
		imageMatix = filter.createImageMatrix();

		assertEquals(10, imageMatix[0].length);
		assertEquals(10, imageMatix.length);
		assertEquals(0, imageMatix[0][0]);
	}
}
