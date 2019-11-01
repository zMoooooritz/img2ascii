package kit.com.moritz.filter;

import java.awt.*;

/**
 * This class extends the AbstractFilter and is a filter that filters the image
 */
public class NormalFilter extends AbstractFilter {

    @Override
    int filterPixel(Color pixelColor) {
        int sum = pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue();
        sum += 3 * brightness;
        sum = sum < 0 ? 0 : sum;
        sum = sum > 765 ? 765 : sum;
        return sum;
    }
}
