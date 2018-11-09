package webcam;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.util.jh.JHGrayFilter;

/**
 * @author Nathan Hartman Date created: Friday, January 18th. 2017 Date
 *         completed: Tuesday, January 18th, 2017
 *
 *         This class implements a webcamimagetransformer interface, creates a
 *         new object of the filter, and returns a buffered image with the
 *         correct filter applied
 */

public class GreyTransformer implements WebcamImageTransformer {

	// Creates new filter
	JHGrayFilter GRAY = new JHGrayFilter();

	public BufferedImage transform(BufferedImage image) {
		// Applies and returns filter
		return GRAY.filter(image, null);
	}

}
