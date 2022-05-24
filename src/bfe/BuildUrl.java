package bfe;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BuildUrl {
	
	private BuildUrl() {
		// this class cannot be instantiated
	}

	public static String[] getIds(String gender, String year, String level, String fase, JComboBox<String> group) {

		StringBuilder builder = new StringBuilder();

		builder.append(getFirstChar(gender));
		builder.append(getSecondChar(year, gender));
		builder.append(getThirdChar(level));
		builder.append(getFourthChar(fase));
		builder.append(getFifthChar(group));

		System.out.println("Builder: " + builder.toString());
		
		return scanDatas(builder.toString());

	}

	public static char getFirstChar(String gender) {

		char output;
		output = gender.equals("Maschile") ? 'm' : 'f';
		return output;
	}

	public static char getSecondChar(String year, String gender) {

		char output = switch (year) {

		case "C Gold" -> 'a';
		case "C Silver" -> 'b';
		case "Serie D" -> 'c';
		case "Promozione" -> {
			char result = gender.equals("Maschile") ? 'd' : 'p';
			yield result;
		}
		case "Prima Divisione" -> 'e';
		case "Seconda Divisione" -> 'f';
		case "U20" -> 'g';
		case "U19" -> {
			char result = gender.equals("Maschile") ? 'h' : 'q';
			yield result;
		}
		case "U17" -> {
			char result = gender.equals("Maschile") ? 'i' : 'r';
			yield result;
		}
		case "U16" -> 'j';
		case "U15" -> {
			char result = gender.equals("Maschile") ? 'k' : 's';
			yield result;
		}
		case "U14" -> {
			char result = gender.equals("Maschile") ? 'l' : 't';
			yield result;
		}
		case "U13" -> {
			char result = gender.equals("Maschile") ? 'm' : 'u';
			yield result;
		}
		case "Serie B" -> 'n';
		case "Serie C" -> 'o';

		default -> {
			JOptionPane.showMessageDialog(null,
					"Errore nel riconoscimento dei gironi! Effettua una segnalazione mediante il pulsante \"Mail\" in basso a destra!");
			yield '@';
		}

		};

		return output;
	}

	public static char getThirdChar(String level) {

		char output = switch (level) {

		case "Eccellenza" -> '1';
		case "Elité" -> '2';
		case "Gold" -> '3';
		case "Silver" -> '4';
		case "Regionale" -> '5';
		default -> {
			JOptionPane.showMessageDialog(null,
					"Errore nel riconoscimento dei gironi! Effettua una segnalazione mediante il pulsante \"Mail\" in basso a destra!");
			yield '@';
		}

		};

		return output;
	}

	public static char getFourthChar(String fase) {

		char output = fase.equals("Qualificazione") ? 'q' : 'c';

		return output;
	}

	private static char getFifthChar(JComboBox<String> group) {

		char output;
		char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '%', '&', '$' };
		output = alphabet[group.getSelectedIndex()];

		return output;
	}

	private static String[] scanDatas(String code) {

		String[] ids = new String[4];
		String line;
		String[] arrayLine = new String[5];
		String idstart = null;
		String idend = null;
		String idgroup = null;

		try {

			BufferedReader br = new BufferedReader(new FileReader("./database/groups.txt"));

			while ((line = br.readLine()) != null) {

				arrayLine = line.split(";");

				if (code.equals(arrayLine[0])) {
					idstart = arrayLine[1];
					idend = arrayLine[2];
					idgroup = arrayLine[3];
					break;
				}

			}

			br.close();

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Il file 'groups.txt' è assente o danneggiato!\n" + ex);
		}

		ids[0] = idstart;
		ids[1] = idend;
		ids[2] = idgroup;
		ids[3] = code;
		return ids;
	}

}
