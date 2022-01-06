package bfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class IndividualStats implements ActionListener {

	private FrameB frame;

	public IndividualStats(FrameB frame) {

		this.frame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String player = frame.getPlayerName().getText();
		int index = getPlayerIndex(player);

		if (index == -1) {
			JOptionPane.showMessageDialog(frame,
					"Giocatore inesistente! Assicurati di aver digitato correttamente il suo cognome!");
			return;
		}

		frame.getIndividualModel().setRowCount(0);
		frame.getPlayerName().setText("");

		fillTable(index);

	}

	private int getPlayerIndex(String player) {

		int index = -1;
		int counter = 0;
		String[] inputParts = player.split(" ", 2);

		for (String temp : frame.getPlayers()) {

			String[] currentParts = temp.split(" ", 2);
			
			if (inputParts[0].equalsIgnoreCase(currentParts[0])) {

				if (inputParts.length == 1) {

					index = counter;
			
				} else { //basically if == 2
					
					if (currentParts[1].contains(inputParts[1])) {
						
						index = counter;
					}
				}
			}
			
			counter ++;
		}

		return index;
	}

	private void fillTable(int index) {

		String[] row = new String[10];

		String player = frame.getPlayers().get(index);
		String games = frame.getGames().get(index).toString();
		String points = frame.getPoints().get(index).toString();
		String tls = frame.getTls().get(index).toString();
		String twos = frame.getTwos().get(index).toString();
		String threes = frame.getThrees().get(index).toString();

		row[0] = player;
		row[1] = games;
		row[2] = points;
		row[4] = tls;
		row[6] = twos;
		row[8] = threes;

		row[3] = getPercentage(points, games);
		row[5] = getPercentage(tls, games);
		row[7] = getPercentage(twos, games);
		row[9] = getPercentage(threes, games);

		frame.getIndividualModel().addRow(row);

	}

	private String getPercentage(String value, String games) {

		double percDouble = (double) (Integer.parseInt(value)) / (Integer.parseInt(games));
		double percDoubleAdjusted = ((int) (percDouble * 10)) / 10.0;
		String perc = Double.toString(percDoubleAdjusted);

		return perc;

	}

}
