package kit.com.moritz.gui;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The panel that contains one image either the preview image or the filtered preview image
 */
public class ImagePanel extends JPanel {
	private int sizeX, sizeY;

	private BufferedImage drawImage;

	public ImagePanel(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		fillImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		g2.drawImage(drawImage, null, 0, 0);
	}

	public void drawImage(BufferedImage image) {
		fillImage();
		BufferedImage resizedImage = resize(image);

		Graphics2D g2 = drawImage.createGraphics();
		int xPos = (int) ((sizeX - resizedImage.getWidth()) / 2);
		int yPos = (int) ((sizeY - resizedImage.getHeight()) / 2);
		g2.drawImage(image, xPos, yPos, resizedImage.getWidth(), resizedImage.getHeight(), null);

	}

	private void fillImage() {
		drawImage = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				drawImage.setRGB(i, j, new Color(50, 50, 50).getRGB());
			}
		}
	}

	private BufferedImage resize(@NotNull BufferedImage img) {
		double scale = determineImageScale(img.getWidth(), img.getHeight());

		int width = (int) (img.getWidth() * scale);
		int height = (int) (img.getHeight() * scale);

		BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	@Contract(pure = true)
	private double determineImageScale(int sourceWidth, int sourceHeight) {
		double scaleX = (double) sizeX / sourceWidth;
		double scaleY = (double) sizeY / sourceHeight;
		return Math.min(scaleX, scaleY);
	}
}