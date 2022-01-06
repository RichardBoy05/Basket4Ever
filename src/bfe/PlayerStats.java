package bfe;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PlayerStats {
	
	private SecondThread secondThread;
	
	public PlayerStats(SecondThread secondThread) {
		this.secondThread = secondThread;
	}
	

	public void statsPlayer(String gender, String id1, String id2, JProgressBar bar, JLabel text) {

		int idstart = Integer.parseInt(id1);
		int idend = Integer.parseInt(id2);
		int totalgames = idend - idstart + 1;
		int currentMatch = 0;

		ArrayList<String> totPlayers = new ArrayList<>();
		ArrayList<Integer> totPoints = new ArrayList<>();
		ArrayList<Integer> totGames = new ArrayList<>();
		ArrayList<Integer> totTls = new ArrayList<>();
		ArrayList<Integer> totTwos = new ArrayList<>();
		ArrayList<Integer> totThrees = new ArrayList<>();

		for (int i = idstart; i <= idend; i++) {

			final String url = "https://lombardiacanestro.it/" + gender + "/Tabellino?idgame=" + i;
			Document html = null;
			try {
				html = Jsoup.connect(url).get();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Impossibile connettersi al sito \"https://lombardiacanestro.it!\"."
						+ "Effettua una segnalazione mediante il pulsante \"Mail\" in basso a destra!");
			}

			ArrayList<String> players = getPlayersList(html, gender);
			ArrayList<String> points = getPointsList(html, gender);
			ArrayList<String> others = getOthersList(html);

			if (points.size() > 0) {

				for (int j = 0; j < points.size(); j++) {

					totPlayers.add(players.get(j));
					totPoints.add(Integer.parseInt((points.get(j))));

				}

				for (int j = 0; j < others.size(); j++) {

					switch (j % 3) {

					case 0 -> {
						totTls.add(Integer.parseInt(others.get(j)));
					}

					case 1 -> {
						totTwos.add(Integer.parseInt(others.get(j)));

					}

					case 2 -> {
						totThrees.add(Integer.parseInt(others.get(j)));

					}

					default -> JOptionPane.showMessageDialog(null,
							"Errore nell'analisi delle statistiche del girone! Effettua una segnalazione!");

					}
				}
			}

			currentMatch++;
			handleProgressBar(bar, text, currentMatch, totalgames);
		}

		int i = 0;
		int max = totPoints.size() - 1;

		while (i <= max) {

			String currentPlayer = totPlayers.get(i);
			int amount = totPoints.get(i);
			int amounttls = totTls.get(i);
			int amounttwos = totTwos.get(i);
			int amountthrees = totThrees.get(i);
			int games = 1;
			totPlayers.remove(i);
			totPoints.remove(i);
			totTls.remove(i);
			totTwos.remove(i);
			totThrees.remove(i);
			int index = totPlayers.indexOf(currentPlayer);

			while (index != -1) {
				amount += totPoints.get(index);
				amounttls += totTls.get(index);
				amounttwos += totTwos.get(index);
				amountthrees += totThrees.get(index);
				games += 1;
				totPlayers.remove(index);
				totPoints.remove(index);
				totTls.remove(index);
				totTwos.remove(index);
				totThrees.remove(index);
				index = totPlayers.indexOf(currentPlayer);
			}

			totPlayers.add(i, currentPlayer);
			totPoints.add(i, amount);
			totTls.add(i, amounttls);
			totTwos.add(i, amounttwos);
			totThrees.add(i, amountthrees);
			totGames.add(i, games);

			max = totPoints.size() - 1;

			i++;
		}
		
		secondThread.setPlayers(totPlayers);
		secondThread.setPoints(totPoints);
		secondThread.setTls(totTls);
		secondThread.setTwos(totTwos);
		secondThread.setThrees(totThrees);
		secondThread.setGames(totGames);
		
		
		bar.setMaximum(100);

		return;
	}

	private static ArrayList<String> getPlayersList(Document html, String gender) {

		String identifier = gender.equals("Maschile") ? "mas" : "fem";
		Elements players = html.getElementsByTag("td");

		Elements quarters = html.getElementsByClass("btn btn-sm bg-" + identifier + " text-white");
		int startValue;

		if (quarters.size() > 0) {
			startValue = 5;
		} else {
			startValue = 3;
		}

		ArrayList<String> giocatori = new ArrayList<>();

		int i = 0;

		for (Element player : players) {
			String text = player.wholeText();

			try {

				@SuppressWarnings("unused")
				int number = Integer.parseInt(text);

			} catch (NumberFormatException nfe) {

				if (i > startValue && !text.equals("-") && !text.equals("NE")) {

					giocatori.add(text);

					if (text.equals("Allenatore")) {
						giocatori.remove(giocatori.size() - 1);
						giocatori.remove(giocatori.size() - 1);
					}

				}

			}

			i++;
		}

		return giocatori;
	}

	private static ArrayList<String> getPointsList(Document html, String gender) {

		String identifier = gender.equals("Maschile") ? "mas" : "fem";
		Elements points = html
				.getElementsByClass("text-center align-middle bg-" + identifier + "-secondary text-white");
		ArrayList<String> punti = new ArrayList<>();

		for (Element point : points) {
			String text = point.wholeText();
			if (text.equals("NE")) {
				text = "0";
			}
			punti.add(text);
		}

		return punti;
	}

	private static ArrayList<String> getOthersList(Document html) {

		Elements stats = html.getElementsByClass("text-center align-middle d-none d-md-table-cell");
		ArrayList<String> statistiche = new ArrayList<>();

		for (Element point : stats) {
			String text = point.wholeText();
			if (text.equals("NE") || text.equals("-")) {
				text = "0";
			}
			statistiche.add(text);
		}
		return statistiche;
	}

	private static void handleProgressBar(JProgressBar bar, JLabel text, int counter, int totalgames) {

		double percxgame = (double) 100 / totalgames;
		percxgame = Math.round(percxgame * 100.0) / 100.0;

		if (percxgame % 1 == 0) {
			bar.setValue(bar.getValue() + (int) percxgame);
		} else {

			bar.setMaximum(10000);
			bar.setValue(bar.getValue() + (int) (percxgame * 100));

		}

		text.setText("Partite analizzate: " + counter + "/" + totalgames);

	}

}
