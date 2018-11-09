package webcam;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.WebcamImageTransformer;
import com.jhlabs.image.TwirlFilter;

/**
 * @author Nathan Hartman Date created: Friday, January 18th. 2017 Date
 *         completed: Tuesday, January 18th, 2017
 *
 *         This class implements a webcamimagetransformer interface, creates a
 *         new object of the filter, and returns a buffered image with the
 *         correct filter applied
 */

public class TwirlTransformer implements WebcamImageTransformer {

	// Creates new filter
	TwirlFilter twirl = new TwirlFilter();

	public BufferedImage transform(BufferedImage image) {

		// Modifies twirl filter slightly
		twirl.setRadius(640);
		twirl.setAngle(7);
		// Applies and returns filter
		return twirl.filter(image, null);
	}

}
