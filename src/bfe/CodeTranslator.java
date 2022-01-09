package bfe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class CodeTranslator {

	private CodeTranslator() {
		// this class cannot be instantiated
	}

	public static String translateCode(String code) {

		String result = "";

		if (code.charAt(0) == 'm') {
			result += "Maschile, ";
		} else {
			result += "Femminile, ";

		}

		result += switch (code.charAt(1)) {

		case 'a' -> "C Gold";
		case 'b' -> "C Silver";
		case 'c' -> "Serie D";
		case 'd' -> "Promozione";
		case 'e' -> "Prima Divisione";
		case 'f' -> "Seconda Divisione";
		case 'g' -> "U20";
		case 'h' -> "U19";
		case 'i' -> "U17";
		case 'j' -> "U16";
		case 'k' -> "U15";
		case 'l' -> "U14";
		case 'm' -> "U13";
		case 'n' -> "Serie B";
		case 'o' -> "Serie C";
		case 'p' -> "Promozione";
		case 'q' -> "U19";
		case 'r' -> "U17";
		case 's' -> "U15";
		case 't' -> "U14";
		case 'u' -> "U13";

		default -> {
			JOptionPane.showMessageDialog(null,
					"Errore nel riconoscimento dei gironi! Effettua una segnalazione mediante il pulsante \"Mail\" in basso a destra!");
			yield "Errore di salvataggio!";
		}

		};

		result += ", ";

		result += switch (code.charAt(2)) {

		case '1' -> "Eccellenza";
		case '2' -> "Elité";
		case '3' -> "Gold";
		case '4' -> "Silver";
		case '5' -> "Regionale";

		default -> {
			JOptionPane.showMessageDialog(null,
					"Errore nel riconoscimento dei gironi! Effettua una segnalazione mediante il pulsante \"Mail\" in basso a destra!");
			yield "Errore di salvataggio!";
		}

		};

		result += ", ";

		if (code.charAt(3) == 'q') {
			result += "Qualificazione, ";
		} else {
			result += "Classificazione, ";

		}

		result += getGroupName(code);

		return result;

	}

	private static String getGroupName(String code) {

		String line;
		String[] arrayline = new String[5];

		try {

			BufferedReader br = new BufferedReader(new FileReader("./database/groups.txt"));

			while ((line = br.readLine()) != null) {

				arrayline = line.split(";");
				if (arrayline[0].equals(code)) {
					br.close();
					return arrayline[4];
				}

			}

			br.close();

		} catch (IOException exc) {

			JOptionPane.showMessageDialog(null, exc);
		}

		return null;

	}

}
