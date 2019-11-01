package kit.com.moritz.calc;

import kit.com.moritz.filter.*;
import kit.com.moritz.gui.MainFrame;
import kit.com.moritz.util.Constants;
import kit.com.moritz.util.FileAccess;
import kit.com.moritz.util.Logger;
import kit.com.moritz.util.StringUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class controls the main part of the conversion from an image to the ascii-art
 */
public class ASCIIConverter {
	private int[][] imageMatrix;
	private boolean settingsUnchanged = false;

	private int blockWidth = 0, blockHeight = 0;

	private int brightness = 0, tolerance = 0, filterIndex = 0;

	private Color filterColor = null;

	private BufferedImage image = null;

	private File imageFile;

	private MainFrame mainFrame;

	private Map<Integer, AbstractFilter>  filterMap;

	/**
	 * The constructor takes the mainframe and initializes it and furthermore initializes the map of all abstract filters
	 *
	 * @param mainFrame the main frame of the application
	 */
	public ASCIIConverter(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		initFilterMap();

		Logger.info("Created the ASCII-Converter and initialized the filter map");
	}

	/**
	 * This method creates the Preview of the current image that will be displayed below the original image
	 */
	public void createPreview() {
		mainFrame.fetchUIData();

		if (!settingsUnchanged) {
			createImageMatrix();
		}

		mainFrame.setFilteredImagePreview(createPreviewImage());
		SwingUtilities.updateComponentTreeUI(mainFrame);

		Logger.info("FilterImagePreview has be created and the filter preview image has been set");
	}

	/**
	 * This method creates the ascii string of the current image and pastes it to the system clipboard
	 */
	public void copyToClipboard() {
		mainFrame.fetchUIData();

		if (!settingsUnchanged) {
			createImageMatrix();
		}

		String asciiString = StringUtil.matrixToString(imageMatrix);

		StringSelection stringSelection = new StringSelection(asciiString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		Logger.info("ASCII_String has be created and pasted into the system clipboard");
	}

	/**
	 * This method creates a text file with the ascii art of the current image in it
	 */
	public void createFile() {
		mainFrame.fetchUIData();

		if (!settingsUnchanged) {
			createImageMatrix();
		}

		String asciiString = StringUtil.matrixToString(imageMatrix);
		String path = imageFile.getPath().substring(0, imageFile.getPath().lastIndexOf('.')) + ".txt";

		FileAccess.clearFile(path);
		FileAccess.appendText(path, asciiString);

		Logger.info("ASCII_String has be created and pasted into the file");
	}

	/**
	 * This method creates the underlying image-matrix that will be used for the following calculations
	 */
	private void createImageMatrix() {
		AbstractFilter filter = filterMap.get(filterIndex);
		filter.initValues(image, blockWidth, blockHeight, brightness, filterColor, tolerance);

		imageMatrix = filter.createImageMatrix();

		settingsUnchanged = true;

		Logger.info("ImageMatrix has successfully been calculated and created");
	}

	/**
	 * This method takes the beforehand created image-matrix and creates from this the preview image and returns it
	 *
	 * @return the resulting previewimage of the current immage
	 */
	private BufferedImage createPreviewImage() {
		int width =  (image.getWidth() / blockWidth) * Constants.DEFAULT_BLOCK_WIDTH;
		int height = (image.getHeight() / blockHeight) * Constants.DEFAULT_BLOCK_HEIGHT;

		BufferedImage previewImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int color = imageMatrix[i / Constants.DEFAULT_BLOCK_WIDTH][j / Constants.DEFAULT_BLOCK_HEIGHT];
				previewImage.setRGB(i, j, new Color(color, color, color).getRGB());
			}
		}

		return previewImage;
	}

	/**
	 * This method takes an image-file and reads the image, this image will be set as the new preview image
	 *
	 * @param imageFile the file of the image that will be used
	 */
	public void setImage(File imageFile) {
		this.imageFile = imageFile;
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			Logger.error("Creating an image from the given File failed");
			e.printStackTrace();
		}
		mainFrame.setImagePreview(image);
		this.settingsUnchanged = false;
	}

	/**
	 * Set the dimension of the blocksize to the new received value
	 *
	 * @param dimension the received value
	 */
	public void setBlockSizeValues(Dimension dimension) {
		if (dimension == null) {
			Logger.error("The given dimension is null and will probably result in further errors");
			return;
		}
		if (this.blockWidth != dimension.width || this.blockHeight != dimension.height)
			this.settingsUnchanged = false;

		this.blockWidth = dimension.width;
		this.blockHeight = dimension.height;
	}

	/**
	 * Set the filterIndex to the new received value
	 *
	 * @param filterIndex the received value
	 */
	public void setFilterIndex(int filterIndex) {
		if (this.filterIndex != filterIndex)
			this.settingsUnchanged = false;
		this.filterIndex = filterIndex;
	}

	/**
	 * Set the brightness to the new received value
	 *
	 * @param brightness the received value
	 */
	public void setBrightness(int brightness) {
		if (this.brightness != brightness)
			this.settingsUnchanged = false;
		this.brightness = brightness;
	}

	/**
	 * Set the filterColor to the new received value
	 *
	 * @param filterColor the received value
	 */
	public void setFilterColor(Color filterColor) {
		if (this.filterColor != filterColor)
			this.settingsUnchanged = false;
		this.filterColor = filterColor;
	}

	/**
	 * Set the tolerance to the new received value
	 *
	 * @param tolerance the received value
	 */
	public void setTolerance(int tolerance) {
		if (this.tolerance != tolerance)
			this.settingsUnchanged = false;
		this.tolerance = tolerance;
	}

	/**
	 * Initialize the filter map with all given filters
	 */
	private void initFilterMap() {
		filterMap = new HashMap<>();

		filterMap.put(0, new StandardFilter());
		filterMap.put(1, new NormalFilter());
		filterMap.put(2, new InvertFilter());
		filterMap.put(3, new IncludeFilter());
		filterMap.put(4, new ExcludeFilter());
	}

}
