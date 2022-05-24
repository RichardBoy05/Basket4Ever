package bfe;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Screenshot implements ActionListener {

	private FrameB frame;

	private Properties properties = new Properties();
	private FileInputStream inp;
	private FileOutputStream out;

	public Screenshot(FrameB frame) {

		this.frame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		BufferedImage image = takeScreenshot();

		String file = getDirectory();

		if (file != null) {

			try {
				ImageIO.write(image, "png", new File(file));
			} catch (IOException e1) {

				JOptionPane.showMessageDialog(null, "Errore di I/O\n" + e1);
			}
		}

	}

	private String getDirectory() {

		File fileToSave = null;
		String directory = null;

		JFileChooser fc = null;
		String lastDir = null;

		try {

			inp = new FileInputStream("./config.properties");
			properties.load(inp);
			inp.close();

			lastDir = properties.getProperty("lastDir");

		} catch (IOException e) {

			JOptionPane.showMessageDialog(frame,
					"Il file \"config.properties\" è assente o danneggiato! Impossibile riconoscere l'ultimo percorso selezionato!");
		}

		LookAndFeel previousLF = UIManager.getLookAndFeel();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			fc = new JFileChooser(lastDir);
			UIManager.setLookAndFeel(previousLF);
		} catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException
				| ClassNotFoundException e) {

			JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'immagine! (" + e + ")");
		}

		fc.setDialogTitle("Scegli dove salvare lo screenshot");
		fc.setFileFilter(new FileNameExtensionFilter("PNG (Portable Network Graphics)", "png"));
		int userChoice = fc.showSaveDialog(frame);

		if (userChoice == JFileChooser.APPROVE_OPTION) {

			fileToSave = fc.getSelectedFile();

			directory = fileToSave.getAbsolutePath();

			if (!directory.substring(directory.length() - 4).equals(".png")) {
				directory = directory + ".png";
			}

			try {
				
				String lastPath = directory;
				lastPath = lastPath.substring(0, lastPath.lastIndexOf("\\"));
				
				out = new FileOutputStream("./config.properties");
				properties.setProperty("lastDir", lastPath);
				properties.store(out, null);
				out.close();
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame,
						"Il file \"config.properties\" è assente o danneggiato, impossibile salvare il percorso!");
			}
		}

		return directory;

	}

	private BufferedImage takeScreenshot() {

		BufferedImage image = null;

		Rectangle screen = new Rectangle(frame.getFrameSize());
		screen.setLocation(frame.getLocation());

		try {

			Robot robot = new Robot();
			image = robot.createScreenCapture(screen);

		} catch (AWTException e1) {

			JOptionPane.showMessageDialog(null, "Errore nella creazione del file\n" + e1);
		}

		return image;
	}

}
