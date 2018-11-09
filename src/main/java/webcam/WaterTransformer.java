package webcam;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.WebcamImageTransformer;
import com.jhlabs.image.WaterFilter;

/**
 * @author Nathan Hartman Date created: Friday, January 18th. 2017 Date
 *         completed: Tuesday, January 18th, 2017
 *
 *         This class implements a webcamimagetransformer interface, creates a
 *         new object of the filter, and returns a buffered image with the
 *         correct filter applied
 */

public class WaterTransformer implements WebcamImageTransformer {

	// Creates new filter
	WaterFilter wtr = new WaterFilter();

	public BufferedImage transform(BufferedImage image) {

		// Modifies wave filter slightly
		wtr.setWavelength(48);
		wtr.setRadius(640);
		wtr.setAmplitude(1);
		// Applies and returns filter
		return wtr.filter(image, null);
	}

}
