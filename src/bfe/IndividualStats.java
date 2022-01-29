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
			
			frame.getIndividualModel().setRowCount(0);
			frame.getIndividualModel().addRow(frame.getEmptyIndividualRow());
			
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

		String[] row = new String[11];

		String player = Utils.capitalizeString(frame.getPlayers().get(index));
		String playerHome = Utils.capitalizeString(frame.getPlayerHomes().get(index));
		String games = frame.getGames().get(index).toString();
		String points = frame.getPoints().get(index).toString();
		String tls = frame.getTls().get(index).toString();
		String twos = frame.getTwos().get(index).toString();
		String threes = frame.getThrees().get(index).toString();

		row[0] = player;
		row[1] = playerHome;
		row[2] = games;
		row[3] = points;
		row[5] = tls;
		row[7] = twos;
		row[9] = threes;

		row[4] = getPercentage(points, games);
		row[6] = getPercentage(tls, games);
		row[8] = getPercentage(twos, games);
		row[10] = getPercentage(threes, games);

		frame.getIndividualModel().addRow(row);
		
	}

	private String getPercentage(String value, String games) {

		double percDouble = (double) (Integer.parseInt(value)) / (Integer.parseInt(games));
		double percDoubleAdjusted = ((int) (percDouble * 10)) / 10.0;
		String perc = Double.toString(percDoubleAdjusted);

		return perc;

	}

}
