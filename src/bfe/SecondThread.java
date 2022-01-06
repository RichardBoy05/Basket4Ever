package bfe;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class SecondThread extends SwingWorker<Void, String> {

	private String gender;
	private String[] ids;
	private JProgressBar bar;
	private JLabel text;
	private JFrame frame;

	private List<String> players = new ArrayList<>();
	private List<Integer> points = new ArrayList<>();
	private List<Integer> games = new ArrayList<>();
	private List<Integer> tls = new ArrayList<>();
	private List<Integer> twos = new ArrayList<>();
	private List<Integer> threes = new ArrayList<>();
	private int[] indexes = new int[10];
	private String[][] teamStats;

	public SecondThread(String gender, String[] ids, JProgressBar bar, JLabel text, JFrame frame) {

		this.gender = gender;
		this.ids = ids;
		this.bar = bar;
		this.text = text;
		this.frame = frame;

	}

	@Override
	protected Void doInBackground() {

		setTeamStats(TeamStats.statsGroup(gender, ids[2]));
		PlayerStats ps = new PlayerStats(this);
		ps.statsPlayer(gender, ids[0], ids[1], bar, text);

		return null;
	}

	@Override
	protected void done() { // getting back on EDT

		frame.dispose();
		showGui();

	}

	private void showGui() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					
					FrameB frame = new FrameB(teamStats, players, points, tls, twos, threes, games);
					frame.setVisible(true);					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Errore nell'apertura dell'interfaccia\n" + e);
				}

			}

		});

	}

	// Getters and Setters

	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}

	public List<String> getPlayers() {
		return players;
	}

	public void setPoints(ArrayList<Integer> points) {
		this.points = points;
	}

	public List<Integer> getPoints() {
		return points;
	}

	public void setGames(ArrayList<Integer> games) {
		this.games = games;
	}

	public List<Integer> getGames() {
		return games;
	}

	public void setTls(ArrayList<Integer> tls) {
		this.tls = tls;
	}

	public List<Integer> getTls() {
		return tls;
	}

	public void setTwos(ArrayList<Integer> twos) {
		this.twos = twos;
	}

	public List<Integer> getTwos() {
		return twos;
	}

	public void setThrees(ArrayList<Integer> threes) {
		this.threes = threes;
	}

	public List<Integer> getThrees() {
		return threes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setTeamStats(String[][] teamStats) {
		this.teamStats = teamStats;
	}

	public String[][] getTeamStats() {
		return teamStats;
	}

}
