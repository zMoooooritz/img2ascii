package kit.com.moritz.filter;

import kit.com.moritz.util.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This abstract class provides a frame for filters that can extend this class and implement the abstract methods
 */
public abstract class AbstractFilter extends Thread {

    private BufferedImage image;
    private int blockWidth;
    private int blockHeight;
    int brightness;
    Color filterColor;
    int tolerance;

    /**
     * This method initializes all required values for the filter
     *
     * @param image the image to convert to a matrix
     * @param blockWidth the width of a single block
     * @param blockHeight the height of a single block
     * @param brightness the brightness of the resulting picture
     * @param filterColor the color to filter for some filters (could be null)
     * @param tolerance the tolerance to the given filterColor
     */
    public void initValues(BufferedImage image, int blockWidth, int blockHeight, int brightness, Color filterColor, int tolerance) {
        this.image = image;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.brightness = brightness;
        this.filterColor = filterColor;
        this.tolerance = tolerance;

        Logger.info("Initialized a Filter with the following values: image-" + image + " blockWidth-" + blockWidth
        + " blockHeight-" + blockHeight + " brightness-" + brightness + " filterColor-" + filterColor +
                " tolerance-" + tolerance);
    }

    /**
     * This method converts the given image into a int-matrix while taking the other parameters into account
     *
     * @return the calculated int-matrix
     */
    public int[][] createImageMatrix() {
        int[][] imageMatrix = new int[(int) Math.ceil((double) image.getWidth() / (double) blockWidth)]
                [(int) Math.ceil((double) image.getHeight() / (double) blockHeight)];

        for (int i = 0; i < image.getWidth(); i += blockWidth) {
            for (int j = 0; j < image.getHeight(); j += blockHeight) {
                imageMatrix[Math.floorDiv(i, blockWidth)][Math.floorDiv(j, blockHeight)] = getAreaBrightness(i, j);
            }
        }
        Logger.info("Successfully converted the image into a int-matrix dimensions: width-" + imageMatrix[0].length + " height-" + imageMatrix.length + " block(s)");
        return imageMatrix;
    }

    /**
     * This method takes a block of pixels of the image and calculates the average brightness/darkness
     *
     * @param startX the start width of the image for the block
     * @param startY the start height of the image for the block
     * @return the average brightness of the block
     */
    private int getAreaBrightness(int startX, int startY) {
        int sum = 0;
        int numPixel = Math.min(blockWidth, image.getWidth() - startX) * Math.min(blockHeight, image.getHeight() - startY);

        for (int i = startX; i < startX + blockWidth && i < image.getWidth(); i++) {
            for (int j = startY; j < startY + blockHeight && j < image.getHeight(); j++) {
                sum += filterPixel(new Color(image.getRGB(i, j)));
            }
        }
        return Math.floorDiv(sum, 3 * numPixel);
    }

    /**
     * This method takes a Color and converts it into an int value with the given filter settins
     *
     * @param pixelColor the color the convert into and int value by the filter
     * @return a int value between 0 and 765
     */
    abstract int filterPixel(Color pixelColor);
}
