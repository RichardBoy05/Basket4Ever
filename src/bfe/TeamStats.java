package bfe;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TeamStats {
	
	private TeamStats() {
		// this class cannot be instantiated
	}

	public static String[][] statsGroup(String gender, String id) {

		final String url = "https://lombardiacanestro.it/" + gender + "/Calendar?id=" + id;
		Document html = null;
		int i = 0;
		int j = 1;

		try {
			html = Jsoup.connect(url).get();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Impossibile connettersi al sito \"https://lombardiacanestro.it\"!\n"
					+ "Verifica di essere connesso ad Internet, altrimenti effettua una segnalazione!");
		}

		Elements teams = html.getElementsByClass("align-middle text-uppercase");
		Elements stats = html.getElementsByClass("text-center align-middle d-none d-sm-table-cell");

		String[][] results = new String[teams.size()][6];

		for (Element team : teams) {
			results[i][0] = team.wholeText();
			i++;
		}

		i = 0;

		for (Element score : stats) {

			results[i][j] = score.wholeText();
			j++;

			if (j == 6) {
				i++;
				j = 1;
			}

		}

		return results;
	}

}
