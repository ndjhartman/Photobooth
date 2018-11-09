package webcam;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GrayFilter;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.miginfocom.swing.MigLayout;

/**
 * @author Nathan Hartman Date created: Wednesday, January 11th. 2017 Date
 *         completed: Thursday, January 12th, 2017
 *
 *         This class creates a JFrame to display the webcam object. It
 *         furthermore displays the capture button to take pictures
 * 
 *         Requires: Webcam and WebcamPanel for default constructor
 */

@SuppressWarnings("serial")
public class Display extends JFrame {

	// Buttons for GUI
	JButton btnCapture;
	JButton btnFilter;

	// JComponents
	JFrame window;
	JPanel jp;
	JPanel buttonPanel;

	// Icon needed to create the grayed out effect
	ImageIcon disabledIcon;

	// Timer for delay on icon changes
	Timer timer;

	// Forces needed objects to be public to class
	Webcam webcam;
	WebcamPanel panel;
	ImageFilters fw;

	/**
	 * This constructor initializes the webcam, panel, and ImageFilter objects,
	 * and calls the method to create the window
	 * 
	 * @param wc
	 *            (Webcam object)
	 * @param p
	 *            (WebcamPanel object)
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IOException
	 */
	public Display(final Webcam wc, WebcamPanel p) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException, IOException {

		// Initializes Webcam
		webcam = wc;

		// Initializes WebcamPanel
		panel = p;

		// Creates new ImageFilter object
		fw = new ImageFilters(webcam);

		// Creates main panel
		start(panel);
	}

	/**
	 * The start method is used to generate the GUI of the application. It also
	 * calls the paneCreation method.
	 * 
	 * @param panel
	 */
	private void start(WebcamPanel panel) {

		// Main window
		window = new JFrame("Photobooth.");

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creates main pane
		paneCreation();

		// Packs down main window into correct size
		window.pack();

		// Opens window in middle of screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - window.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - window.getHeight()) / 2);
		window.setLocation(x, y);

		// Makes window visible
		window.setVisible(true);

	}

	/**
	 * The paneCreation method generates the main JPanel needed. It also calls
	 * the buttonCreation method
	 */
	private void paneCreation() {

		// Layered pane for components
		jp = new JPanel();
		jp.setMinimumSize(new Dimension(200, 200));
		jp.setBackground(Color.WHITE);
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

		// Adds WebPanel to layered pane
		jp.add(panel);

		// Creates buttons
		buttonCreation();

		// Adds layered pane to window
		window.getContentPane().add(jp);

	}

	/**
	 * The buttonCreation() method creates the JPanel the buttons will be
	 * displayed on It also calls the methods btnCap(), and btnFilt()
	 */
	private void buttonCreation() {

		// JPanel for buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new MigLayout("align 50% 50%", "[][]", "[]"));

		// Creates capture button
		btnCap();

		// Creates filter button
		btnFilt();

		// Adds buttons to pane
		buttonPanel.add(btnCapture, "cell 0 0");
		buttonPanel.add(btnFilter, "cell 1 0");

		// Adds buttonPanel
		jp.add(buttonPanel);

	}

	/**
	 * The btnCap method is used to generate the button used for capturing
	 * images. It also uses a mouseListener to detect mouse clicks on it. It
	 * calls the methods btnClick(), and imgCapture()
	 */
	private void btnCap() {

		// Capture button misc.
		btnCapture = new JButton();
		btnCapture.setBackground(Color.WHITE);
		btnCapture.setOpaque(false);
		btnCapture.setContentAreaFilled(false);
		btnCapture.setBorderPainted(false);

		// Gets URL to image
		URL url1 = Display.class.getResource("/resources/capture.png");

		// Scales and sets icon to the url image
		btnCapture.setIcon(iconScale(url1, 100, 100));
		btnCapture.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCapture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					// Calls btnClick method to temporarily gray the icon
					btnClick(btnCapture, (ImageIcon) btnCapture.getIcon());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					// Calls method to take picture
					imgCapture(webcam);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * The btnFilt() method creates the button used to access the filter
	 * options. It uses a mouseListener to detect clicks It calls the
	 * openClose() method
	 */
	private void btnFilt() {
		// Filter button misc.
		btnFilter = new JButton();
		btnFilter.setBackground(Color.WHITE);

		// Gets URL to image
		URL url = Display.class.getResource("/resources/filters.png");
		// Scales and sets icon to the url image
		btnFilter.setIcon(iconScale(url, 100, 100));
		btnFilter.setAlignmentX(Component.CENTER_ALIGNMENT);

		// On filter button click...
		btnFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Opens or closes filter window
				fw.openClose();
			}

		});

	}

	/**
	 * The iconScale method allows a path in the form of a string to be sent as
	 * well as the width and height as integers for a given icon to be scaled.
	 * It returns an ImageIcon.
	 * 
	 * @param url
	 * @param width
	 * @param height
	 * @return
	 */
	private ImageIcon iconScale(URL url, int width, int height) {

		// Creates new icon with old icon
		ImageIcon icon = new ImageIcon(url);

		// Scales image to specified dimensions
		Image img = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

		// Creates a new icon with the scaled image
		ImageIcon scaledIcon = new ImageIcon(img);

		// Returns the scaled icon
		return scaledIcon;
	}

	/**
	 * The imgCapture method saves an image as a .PNG to the users desktop.
	 * 
	 * @param webcam
	 * @throws IOException
	 */
	private void imgCapture(Webcam webcam) throws IOException {

		// Gets the date and time for file name
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		File file = new File(System.getProperty("user.home") + "/Desktop//" + sdf.format(date) + ".png");

		// Get's image from buffered image stream
		BufferedImage image = webcam.getImage();

		// save image to PNG file
		ImageIO.write(image, "PNG", file);
	}

	/**
	 * The btnClick method is used to create a temporary gray effect on a sent
	 * JComponents icon. It requires the JComponent and the JComponent icon to
	 * be sent to it
	 * 
	 * @param btn
	 * @param icon
	 * @throws InterruptedException
	 */
	private void btnClick(final JButton btn, final ImageIcon icon) throws InterruptedException {

		// Gets icon height and width
		final int w = icon.getIconWidth();
		final int h = icon.getIconHeight();

		// Converts image to gray
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		BufferedImage image = gc.createCompatibleImage(w, h);
		Graphics2D g2d = image.createGraphics();
		icon.paintIcon(null, g2d, 0, 0);
		Image gray = GrayFilter.createDisabledImage(image);

		// Sets icon to disabled one
		disabledIcon = new ImageIcon(gray);
		btn.setIcon(disabledIcon);

		// Waits 600 milliseconds and returns icon to original
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				btn.setIcon(icon);
			}
		};

		// Timer for ActionListener
		timer = new Timer(600, listener);

		// Starts timer
		timer.start();
	}

}
