package bfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDatas implements ActionListener{

	private FrameB frame;
	
	public SaveDatas(FrameB frame) {
		
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		frame.getPlayers(); //delete
		
		//restart from here
	}
	
}
