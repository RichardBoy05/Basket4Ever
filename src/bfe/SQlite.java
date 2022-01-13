package bfe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SQlite {

	private static final String CONNECTION = "jdbc:sqlite:./database/Basket4EverDb.db";
	private static final String TABLE = "saves";

	private SQlite() {
		// this class cannot be instantiated
	}

	private static Connection getConnection() throws Exception {

		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");

			conn = DriverManager.getConnection(CONNECTION);

		} catch (ClassNotFoundException | SQLException e) {

			JOptionPane.showMessageDialog(null,
					"Database mancante! Assicurarsi che il file Basket4EverDb.db sia nella stessa cartella del file eseguibile!\n"
							+ e);
			return null;
		}

		return conn;
	}

	public static void save(String code, String date, String teams, String players, String games, String points,
			String tls, String twos, String threes, JButton button, ImageIcon icon) {

		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement(
					"INSERT INTO " + TABLE + " (code, date, teams, players, games, points, tls, twos, threes) VALUES ('"
							+ code + "','" + date + "','" + teams + "','" + players + "','" + games + "','" + points
							+ "','" + tls + "','" + twos + "','" + threes + "')");

			posted.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database irraggiungibile!\n" + e);
			return;
		}

		button.setIcon(icon);
		button.setRolloverIcon(null);
		JOptionPane.showMessageDialog(null, "Salvataggio effettuato con successo!");
	}

	public static List<String> getSavedList() {

		List<String> list = new ArrayList<String>();

		try {

			Connection con = getConnection();
			PreparedStatement stat = con.prepareStatement("SELECT code, date, id FROM " + TABLE);
			ResultSet result = stat.executeQuery();

			while (result.next()) {

				list.add(result.getString(1));
				list.add(result.getString(2));
				list.add(result.getString(3));

			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Database irraggiungibile!\n" + e);
			return null;
		}

		return list;
	}

	public static List<String> getSavedStats(int id) {

		List<String> list = new ArrayList<String>();

		try {

			Connection con = getConnection();
			PreparedStatement stat = con.prepareStatement(
					"SELECT code, date, teams, players, games, points, tls, twos, threes FROM " + TABLE + " WHERE id = " + id);
			ResultSet result = stat.executeQuery();

			while (result.next()) {

				for (int i = 1; i < 10; i++) {

					list.add(result.getString(i));

				}
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Database irraggiungibile!\n" + e);
			return null;
		}

		return list;
	}

	public static void deleteSaved(int id) {

		try {

			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM " + TABLE + " WHERE id = " + id);

			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Salvataggio eliminato!");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database irraggiungibile!\n" + e);
		}

	}

}
