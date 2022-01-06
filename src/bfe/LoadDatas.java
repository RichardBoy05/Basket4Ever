package bfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadDatas implements ActionListener{
	
		private FrameB frame;
		
		public LoadDatas(FrameB frame) {
			
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			frame.getTeams();  //delete
			
			//restart from here
		}
	
}
