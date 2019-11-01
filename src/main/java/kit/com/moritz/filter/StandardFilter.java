package kit.com.moritz.filter;

import java.awt.*;

/**
 * This class extends the AbstractFilter and is a filter that filters the image
 */
public class StandardFilter extends AbstractFilter {

	@Override
	int filterPixel(Color pixelColor) {
		return pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue();
	}
}
