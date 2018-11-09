package webcam;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.github.sarxos.webcam.Webcam;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Nathan Hartman Date created: Friday, January 13th. 2017 Date
 *         completed: Tuesday, January 17th, 2017
 *
 *         This class creates a JFrame to display the various filter options.
 *         Upon clicking on an option, it sets the webcam image transformer to
 *         the desired filtered object
 * 
 *         Requires: Webcam object
 */

public class ImageFilters {

	// Global JComponents
	JFrame window;
	Webcam webcam;
	JPanel jp;

	// Global filters
	final GreyTransformer gtf;
	final InvertTransformer invtf;
	final PointTransformer pttf;
	final KaleidoscopeTransformer kldstf;
	final WaterTransformer wtrtf;
	final MarbleTransformer mrbltf;
	final TwirlTransformer twltf;
	final EdgeTransformer etf;

	/**
	 * This constructor initializes each class into an object to be called upon.
	 * It requires a Webcam object to be called to it to apply the filter on
	 * 
	 * @param wc
	 *            (Webcam object)
	 */
	public ImageFilters(final Webcam wc) {

		// Initializes webcam
		webcam = wc;

		// Initializes filters
		gtf = new GreyTransformer();
		invtf = new InvertTransformer();
		pttf = new PointTransformer();
		kldstf = new KaleidoscopeTransformer();
		wtrtf = new WaterTransformer();
		mrbltf = new MarbleTransformer();
		twltf = new TwirlTransformer();
		etf = new EdgeTransformer();

		// Creates filter window to display options
		filterWindow();

		// Creates buttons to be displayed
		filterButtons();

	}

	/**
	 * The filterWindow method displays the various filter options as a window
	 */
	private void filterWindow() {

		// Main JFrame misc.
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(100, 100, 450, 300);

		// Main JPanel misc.
		jp = new JPanel();
		jp.setLayout(new GridLayout(3, 3));

		// Adds panel to frame
		window.getContentPane().add(jp);

		// Removes borders from JFrame
		window.setUndecorated(true);

		// Makes window visible
		window.setVisible(false);
	}

	/**
	 * The filterButtons method creates all the buttons and calls correct class
	 * when clicked on
	 * 
	 */
	private void filterButtons() {

		// Creates grayscale button
		JButton gray = new JButton("Gray Scale");
		gray.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets the webcam image transformer to the relevant filter
				webcam.setImageTransformer(gtf);
				// Opens or closes the window
				openClose();
			}
		});
		jp.add(gray);

		// Creates pointillate button
		JButton pointillate = new JButton("Pointillize");
		pointillate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets the webcam image transformer to the relevant filter
				webcam.setImageTransformer(pttf);
				// Opens or closes the window
				openClose();
			}
		});
		jp.add(pointillate);

		// Creates invert button
		JButton invert = new JButton("Invert Colors");
		invert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets the webcam image transformer to the relevant filter
				webcam.setImageTransformer(invtf);
				// Opens or closes the window
				openClose();
			}
		});
		jp.add(invert);

		// Creates water button
		JButton wtr = new JButton("Water");
		wtr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets the webcam image transformer to the relevant filter
				webcam.setImageTransformer(wtrtf);
				// Opens or closes the window
				openClose();
			}
		});
		jp.add(wtr);

		// Creates normal button
		JButton normal = new JButton("Default");
		normal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets the webcam image transformer to the relevant filter
				webcam.setImageTransformer(null);
				// Opens or closes the window
				openClose();
			}
		});
		jp.add(normal);

		// Creates edge detection button
		JButton edge = new JButton("Edge Detection");
		edge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets the webcam image transformer to the relevant filter
				webcam.setImageTransformer(etf);
				// Opens or closes the window
				openClose();
			}
		});
		jp.add(edge);

		// Creates kaleidoscope button
		JButton kldscpe = new JButton("Kaleidoscope");
		kldscpe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets the webcam image transformer to the relevant filter
				webcam.setImageTransformer(kldstf);
				// Opens or closes the window
				openClose();
			}
		});
		jp.add(kldscpe);

		// Creates marble button
		JButton marble = new JButton("Marble");
		marble.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Sets the webcam image transformer to the relevant filter
				webcam.setImageTransformer(mrbltf);
				// Opens or closes the window
				openClose();
			}
		});
		jp.add(marble);

		// Creates twirl button
		JButton twirl = new JButton("Twirl");
		twirl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets the webcam image transformer to the relevant filter
				webcam.setImageTransformer(twltf);
				// Opens or closes the window
				openClose();
			}
		});
		jp.add(twirl);

	}

	// Method to open the filter window
	public void openClose() {

		// Sets window location to the middle of program
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - window.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - window.getHeight()) / 2);
		window.setLocation(x, y);

		// Opens window if closed, closes if open
		if (window.isVisible() == false) {
			window.setLocation(x, y);
			window.setVisible(true);
		} else {
			window.setVisible(false);
		}
	}

}
