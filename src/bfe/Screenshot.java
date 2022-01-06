package bfe;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Screenshot implements ActionListener {

	private FrameB frame;

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
		File pathLastDir = new File("./lastDir.txt");
		String lastDir = null;

		try {

			FileReader reader = new FileReader(pathLastDir);
			BufferedReader b = new BufferedReader(reader);
			lastDir = b.readLine();
			b.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Il file indicante l'ultimo percorso selezionato non risiede nalla stessa cartella del programma!\n"
							+ e);
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

			try {
				PrintWriter printer = new PrintWriter(new FileWriter(pathLastDir, false));
				directory = fileToSave.getAbsolutePath();
				
				if (!directory.substring(directory.length() - 4).equals(".png")) {
					directory = directory + ".png";
				} 
				
				printer.println(directory.substring(0, directory.lastIndexOf("\\")));
				printer.close();
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Errore I/O!" + e);
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
