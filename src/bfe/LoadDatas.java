package bfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class LoadDatas implements ActionListener {

	private FrameB frame;
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

		int choice = showGui(orderedSavedList);
		System.out.println(choice);

	}

	private int showGui(String[][] list) {

		String[] items = new String[list.length];
		int counter = 0;

		for (String[] current : list) {

			items[counter] = current[0] + ", in data " + current[1];

			counter++;

		}

		cb = new JComboBox<String>(items);
		int selection = JOptionPane.showConfirmDialog(frame, cb, "Seleziona il girone", JOptionPane.OK_CANCEL_OPTION);
		int index = cb.getSelectedIndex();
		
		if (selection == 0) {
					
			Object[] choices = {"Mostra", "Elimina"};
			Object defaultChoice = choices[0];
			int secondChoices = JOptionPane.showOptionDialog(frame,
					"Scegli che azione effettuare con il girone selezionato",
					"Scegli un'opzione",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					choices,
					defaultChoice);
			
			if (secondChoices == 0) {
				return index;
			} else {
				return index;
			}
		} 
		
		return -1;

	}

}
