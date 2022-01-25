package bfe;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

public class ViewOnLc implements ActionListener {

	private FrameB frame;

	public ViewOnLc(FrameB frame) {

		this.frame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String code = frame.getCode();

		if (code == null) {
			JOptionPane.showMessageDialog(frame,
					"Impossibile connettersi al sito Lombardia Canestro da questa pagina!");
			return;
		}

		String gender = getGender(code);
		String id = getGroupId(code);

		if (id == null) {

			JOptionPane.showMessageDialog(frame, "Girone inesistente! Impossibile connettersi!");
			return;

		}

		String url = "https://lombardiacanestro.it/" + gender + "/Calendar?id=" + id;

		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException ee) {

			JOptionPane.showMessageDialog(frame, ee);
		}

	}

	private String getGender(String code) {

		String gender = code.charAt(0) == 'm' ? "Maschile" : "Femminile";

		return gender;

	}

	private String getGroupId(String code) {

		String id = null;
		String line;
		String[] arrayLine;

		try {

			BufferedReader br = new BufferedReader(new FileReader("./database/groups.txt"));

			while ((line = br.readLine()) != null) {

				arrayLine = line.split(";");

				if (arrayLine[0].equals(code)) {

					id = arrayLine[3];
				}
			}

			br.close();

		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "Il file 'groups.txt' è assente o danneggiato!\n" + e);
		}

		return id;

	}

}
