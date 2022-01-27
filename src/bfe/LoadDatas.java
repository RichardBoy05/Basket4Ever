package bfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class LoadDatas implements ActionListener {

	private FrameB frame;
	private String[] items = new String[5];
	private JComboBox<String> cb;

	public LoadDatas(FrameB frame) {

		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		List<String> savedList = SQlite.getSavedList();

		String[][] orderedSavedList = new String[savedList.size() / 3][2];
		String ids[] = new String[savedList.size() / 3];

		for (int i = 0, j = 0; i < savedList.size(); i += 3, j++) {

			orderedSavedList[j][0] = CodeTranslator.translateCode(savedList.get(i));
			orderedSavedList[j][1] = savedList.get(i + 1);
			ids[j] = savedList.get(i + 2);
		}

		items = new String[orderedSavedList.length];

		int counter = 0;

		for (String[] current : orderedSavedList) {

			items[counter] = current[0] + ", in data " + current[1];

			counter++;

		}

		String choice = showGui(items);

		if (choice != null) {

			runQuery(choice, ids);

		}

	}

	private String showGui(String[] items) {

		int selection;

		if (items.length == 0) {
			cb = new JComboBox<String>();
			cb.addItem("Nessun salvataggio esistente!");
			selection = JOptionPane.showConfirmDialog(frame, cb, "Seleziona il girone", JOptionPane.OK_CANCEL_OPTION);
			return null;

		} else {

			cb = new JComboBox<String>(items);
			selection = JOptionPane.showConfirmDialog(frame, cb, "Seleziona il girone", JOptionPane.OK_CANCEL_OPTION);

		}

		int index = cb.getSelectedIndex();

		if (selection == 0) {

			Object[] choices = { "Mostra", "Elimina" };

			int showOrDelete = JOptionPane.showOptionDialog(frame,
					"Scegli che azione effettuare con il girone selezionato", "Scegli un'opzione",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			if (showOrDelete == 0) {
				return Integer.toString(index) + "s";
			} else {
				return Integer.toString(index) + "d";
			}
		}

		return null;

	}

	private void runQuery(String choice, String[] ids) {

		int length = choice.length();
		int value;
		int id;

		if (choice.substring(length - 1).equals("s")) {

			value = Integer.parseInt(choice.substring(0, length - 1));
			id = Integer.parseInt(ids[value]);
			showChanges(SQlite.getSavedStats(id));

		} else {

			value = Integer.parseInt(choice.substring(0, length - 1));
			id = Integer.parseInt(ids[value]);
			SQlite.deleteSaved(id);

		}
	}

	private void showChanges(List<String> list) {

		frame.setCode(list.get(0));

		try {
			frame.setDate(frame.getInternalDateFormatter().parse(list.get(1)));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(frame, e);
		}

		frame.setTeams(createTeamsArray(list.get(2)));
		frame.setPlayers(createStringList(list.get(3)));
		frame.setPlayerHomes(createStringList(list.get(4)));
		frame.setGames(createIntegerList(list.get(5)));
		frame.setPoints(createIntegerList(list.get(6)));
		frame.setTls(createIntegerList(list.get(7)));
		frame.setTwos(createIntegerList(list.get(8)));
		frame.setThrees(createIntegerList(list.get(9)));

		frame.getOverViewLab().setText(CodeTranslator.translateCode(frame.getCode()));
		frame.getOverViewLab().setToolTipText("Salvattaggio effettuato in data " + frame.getVisibleDateFormatter().format(frame.getDate()));
		Utils.fixLabelFontSize(frame.getOverViewLab(), false);
		
		frame.fillTables();
		frame.getIndividualModel().setRowCount(0);
		frame.getPlayerName().setText(null);

	}

	private String[][] createTeamsArray(String raw) {

		int length = (int) ((raw.chars().filter(num -> num == ';').count()) + 1);
		String[][] result = new String[length][6];

		String[] teams = raw.split(";");

		for (int i = 0; i < teams.length; i++) {

			String[] currentElements = teams[i].split(", ");

			for (int j = 0; j < currentElements.length; j++) {

				result[i][j] = currentElements[j];
			}

		}

		return result;

	}

	private List<String> createStringList(String element) {

		List<String> result = new ArrayList<>();
		String[] values = element.split(",");

		for (String currentValue : values) {

			result.add(currentValue);

		}

		return result;

	}

	private List<Integer> createIntegerList(String element) {

		List<Integer> result = new ArrayList<>();
		String[] valuesStr = element.split(",");
		int[] values = Arrays.stream(valuesStr).mapToInt(Integer::parseInt).toArray();

		for (int currentValue : values) {

			result.add(currentValue);

		}

		return result;

	}

}
