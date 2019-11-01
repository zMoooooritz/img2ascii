package kit.com.moritz.gui;

import kit.com.moritz.calc.ASCIIConverter;
import kit.com.moritz.util.Constants;
import kit.com.moritz.util.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * THis class contains most of the logic for the GUI
 */
public class MainFrame extends JFrame {

	private final static Color SCREEN_COLOR = new Color(40, 40, 40);

	private HeaderPanel headerPanel;

	private ImageSelectionPanel imageSelectionPanel;

	private DisplayPanel displayPanel;

	private LowerButtonPanel lowerButtonPanel;

	private BlockSizePanel blockSizePanel;

	private FilterPanel filterPanel;

	public BufferedImage toggleButton1On, toggleButton1Off, toggleButton2On, toggleButton2Off;

	ASCIIConverter asciiConverter;

	/**
	 * The main constructor that initializes all required attributes
	 */
	public MainFrame() {
		asciiConverter = new ASCIIConverter(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setLocation(100, 100);
		setUndecorated(true);
		getContentPane().setForeground(SCREEN_COLOR);
		getContentPane().setBackground(SCREEN_COLOR);
		setResizable(false);
		setLayout(new GridBagLayout());

		initImages();

		headerPanel = new HeaderPanel(this);

		imageSelectionPanel = new ImageSelectionPanel(this);

		blockSizePanel = new BlockSizePanel(this);

		filterPanel = new FilterPanel(this);

		displayPanel = new DisplayPanel(this);

		lowerButtonPanel = new LowerButtonPanel(this);

		setVisible(true);

		Logger.info("Successfully created the whole UI");
	}

	/**
	 * Initalize the 'empty' images that will be displayed on start of the application
	 */
	private void initImages() {
		int size1X = 200, size1Y = 30;

		toggleButton1On = new BufferedImage(size1X, size1Y, BufferedImage.TYPE_INT_RGB);
		toggleButton1Off = new BufferedImage(size1X, size1Y, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < size1X; i++) {
			for (int j = 0; j < size1Y; j++) {
				toggleButton1On.setRGB(i, j, new Color(90, 90, 90).getRGB());
				toggleButton1Off.setRGB(i, j, new Color(50, 50, 50).getRGB());
			}
		}

		int size2X = 25, size2Y = 75;

		toggleButton2On = new BufferedImage(size2X, size2Y, BufferedImage.TYPE_INT_RGB);
		toggleButton2Off = new BufferedImage(size2X, size2Y, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < size2X; i++) {
			for (int j = 0; j < size2Y; j++) {
				toggleButton2On.setRGB(i, j, new Color(90, 90, 90).getRGB());
				toggleButton2Off.setRGB(i, j, new Color(50, 50, 50).getRGB());
			}
		}
	}

	/**
	 * Sets the given image as the preview of the image
	 *
	 * @param image the image that has to be used
	 */
	public void setImagePreview(BufferedImage image) {
		displayPanel.inputPanel.drawImage(image);
	}

	/**
	 * Sets the given image as the preview of the filtered image
	 *
	 * @param image the image that has to be used
	 */
	public void setFilteredImagePreview(BufferedImage image) {
		displayPanel.outputPanel.drawImage(image);
	}

	/**
	 * Enables the lower button panel buttons
	 */
	public void enableButtons() {
		lowerButtonPanel.enableButtons();
	}

	/**
	 * Fetch all data that is set in the UI and transmit it afterwards to the ASCIIConverter class
	 */
	public void fetchUIData() {
		blockSizePanel.setBlockSizeValues();
		filterPanel.setFilterIndex();
		filterPanel.setBrightness();
		filterPanel.setFilterColor();
		filterPanel.setTolerance();
	}
}