package bfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class SaveDatas implements ActionListener{

	private final static SimpleDateFormat SQL_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	private final static char DIVIDER = ';';
	
	private FrameB frame;
	
	public SaveDatas(FrameB frame) {
		
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String code = frame.getCode();
		String date = SQL_DATE_FORMATTER.format(frame.getDate());
		
		String teams = matrixConverter(frame.getTeams());
		String players = listConverter(frame.getPlayers());
		String games = listConverter(frame.getGames());
		String points = listConverter(frame.getPoints());
		String tls = listConverter(frame.getTls());
		String twos = listConverter(frame.getTwos());
		String threes = listConverter(frame.getThrees());
		

		System.out.println(teams);

		
	}
	
	private String matrixConverter(String[][] array) {
		
		String result = "";
		
		return result;
		
	}
	
	private String listConverter(List<?> list) {
		
		String result = list.toString();
		result = result.substring(1, result.length() - 1);
		result = result.replaceAll(", ", ",");
			
		return result;
		
	}
	
}
