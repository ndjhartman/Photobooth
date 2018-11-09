package webcam;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.WebcamImageTransformer;
import com.jhlabs.image.MarbleFilter;

/**
 * @author Nathan Hartman Date created: Friday, January 18th. 2017 Date
 *         completed: Tuesday, January 18th, 2017
 *
 *         This class implements a webcamimagetransformer interface, creates a
 *         new object of the filter, and returns a buffered image with the
 *         correct filter applied
 */

public class MarbleTransformer implements WebcamImageTransformer {

	// Creates new filter
	MarbleFilter marble = new MarbleFilter();

	public BufferedImage transform(BufferedImage image) {

		// Modifies default values for marble filter slightly
		marble.setAmount(10);
		marble.setXScale(10);
		marble.setYScale(10);
		// Applies and returns filter
		return marble.filter(image, null);
	}

}
