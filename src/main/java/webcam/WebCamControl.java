package webcam;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

/**
 * @author Nathan Hartman Date started: Monday, January 9th. 2017 Date
 *         completed: Wednesday, January 11th, 2017
 *
 *         This class creates a Webcam object used to access and control various
 *         web cams. It furthermore creates a WebcamPanel for the Webcam object
 *         to be displayed upon.
 */

public class WebCamControl {

	/**
	 * Main method that creates Webcam and Webcampanel objects to interact with
	 * computer webcam, and displays on JFrame object
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException, IOException {

		// Sets theme of of GUI to that of the OS
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		// Creates Webcam object and finds default webcam
		Webcam webcam = Webcam.getDefault();

		// Sets the resolution to the resolution of the webcam
		webcam.setViewSize(WebcamResolution.VGA.getSize());

		// Creates WebcamPanel object to display webcam, and passes webcam
		// object
		WebcamPanel panel = new WebcamPanel(webcam);

		// Enables statistics such as fps and resolution
		panel.setFPSDisplayed(true);
		panel.setFPSLimited(false);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(false);

		// Calls Display class to display webcam, passes Webcam and WebcamPanel
		// objects
		Display dsp = new Display(webcam, panel);

	}
}
