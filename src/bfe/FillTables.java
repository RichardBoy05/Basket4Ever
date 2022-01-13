package bfe;

import javax.swing.JOptionPane;

public class FillTables {

	private FrameB frame;

	public FillTables(FrameB frame) {

		this.frame = frame;

		fillTeams();
		fillPlayers(0);
		fillPlayers(1);
		fillPlayers(2);
		fillPlayers(3);

	}

	private void fillTeams() {

		if (frame.getTeamsModel().getRowCount() > 0) {
			frame.getTeamsModel().setRowCount(0);
		}

		String[][] teams = frame.getTeams();

		for (int i = 0; i < teams.length; i++) {

			String[] row = new String[8];

			int place = i + 1;
			String placeStr = Integer.toString(place);
			String pointsStr = teams[i][2];
			int points = Integer.parseInt(pointsStr);
			points *= 2;
			pointsStr = Integer.toString(points);

			row[0] = placeStr;
			row[1] = teams[i][0];
			row[2] = pointsStr;
			row[3] = teams[i][1];
			row[4] = teams[i][2];
			row[5] = teams[i][3];
			row[6] = teams[i][4];
			row[7] = teams[i][5];

			frame.getTeamsModel().addRow(row);

		}

	}

	private void fillPlayers(int section) {

		switch (section) {

		case 0 -> {
			if (frame.getPlayersModel1().getRowCount() > 0) {
				frame.getPlayersModel1().setRowCount(0);
			}
		}
		
		case 1 -> {
			if (frame.getPlayersModel2().getRowCount() > 0) {
				frame.getPlayersModel2().setRowCount(0);
			}
		}
		
		case 2 -> {
			if (frame.getPlayersModel3().getRowCount() > 0) {
				frame.getPlayersModel3().setRowCount(0);
			}
		}
		
		case 3 -> {
			if (frame.getPlayersModel4().getRowCount() > 0) {
				frame.getPlayersModel4().setRowCount(0);
			}
		}

		}

		int counter = 1;

		switch (section) {

		case 0 -> frame.setIndexes(Sorter.findIndexes(frame.getPoints()));
		case 1 -> frame.setIndexes(Sorter.findIndexes(frame.getTls()));
		case 2 -> frame.setIndexes(Sorter.findIndexes(frame.getTwos()));
		case 3 -> frame.setIndexes(Sorter.findIndexes(frame.getThrees()));

		}

		for (int i = (frame.getIndexes().length - 1); i >= 0; i--) {

			String counterStr = Integer.toString(counter);
			String player = frame.getPlayers().get(frame.getIndexes()[i]);
			String games = frame.getGames().get(frame.getIndexes()[i]).toString();

			String points = switch (section) {

			case 0 -> frame.getPoints().get(frame.getIndexes()[i]).toString();
			case 1 -> frame.getTls().get(frame.getIndexes()[i]).toString();
			case 2 -> frame.getTwos().get(frame.getIndexes()[i]).toString();
			case 3 -> frame.getThrees().get(frame.getIndexes()[i]).toString();
			default -> {
				JOptionPane.showMessageDialog(null, "Errore nell'analisi delle statistiche!");
				yield null;
			}
			};

			double ppgDouble = (double) (Integer.parseInt(points)) / (Integer.parseInt(games));
			double ppgDoubleAdjusted = ((int) (ppgDouble * 10)) / 10.0;
			String ppg = Double.toString(ppgDoubleAdjusted);

			String[] row = { counterStr, player, points, ppg, games };

			switch (section) {

			case 0 -> frame.getPlayersModel1().addRow(row);
			case 1 -> frame.getPlayersModel2().addRow(row);
			case 2 -> frame.getPlayersModel3().addRow(row);
			case 3 -> frame.getPlayersModel4().addRow(row);

			}

			counter++;

		}

	}

}
