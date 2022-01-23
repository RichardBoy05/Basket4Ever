package bfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class SaveDatas implements ActionListener{
	
	private FrameB frame;
	
	public SaveDatas(FrameB frame) {
		
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(frame.getSaveBut().getIcon() == frame.getSavedButIcon()) {
		JOptionPane.showMessageDialog(null, "Hai già salvato questa pagina!");
		return;
		}
		
		String code = frame.getCode();
		String date = frame.getdateFormatter().format(new Date(System.currentTimeMillis()));
		String teams = matrixConverter(frame.getTeams());
		String players = listConverter(frame.getPlayers());
		String playerHomes = listConverter(frame.getPlayerHomes());
		String games = listConverter(frame.getGames());
		String points = listConverter(frame.getPoints());
		String tls = listConverter(frame.getTls());
		String twos = listConverter(frame.getTwos());
		String threes = listConverter(frame.getThrees());
		
		SQlite.save(code, date, teams, players, playerHomes, games, points, tls, twos, threes, frame.getSaveBut(), frame.getSavedButIcon());
	
		
	}
	
	private String matrixConverter(String[][] array) {
		
		String result = Arrays.deepToString(array);
		
		result = result.substring(2, result.length() - 2);
		result = result.replaceAll("], \\[", ";");
		result = result.replaceAll("'", "&");
		
		return result;
		
	}
	
	private String listConverter(List<?> list) {
		
		String result = list.toString();
		result = result.substring(1, result.length() - 1);
		result = result.replaceAll(", ", ",");
		result = result.replaceAll("'", "&");
			
		return result;
		
	}
	
}
