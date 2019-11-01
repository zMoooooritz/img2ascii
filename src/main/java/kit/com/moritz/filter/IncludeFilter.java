package kit.com.moritz.filter;

import java.awt.*;

/**
 * This class extends the AbstractFilter and is a filter that includes / increases a spectrum of colors
 */
public class IncludeFilter extends AbstractFilter {

    @Override
    int filterPixel(Color pixelColor) {
        if (filterColor != null && Math.abs(pixelColor.getRed() - filterColor.getRed()) <= tolerance
                && Math.abs(pixelColor.getGreen() - filterColor.getGreen()) <= tolerance
                && Math.abs(pixelColor.getBlue() - filterColor.getBlue()) <= tolerance)
            return 3 * brightness;
        else
            return pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue();
    }

}
