package bfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoadDatas implements ActionListener {

	private FrameB frame;

	public LoadDatas(FrameB frame) {

		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		List<String> savedTitles = SQlite.getSavedList();
		
		String code = CodeTranslator.translateCode(frame.getCode());
		
		
	}
		

	

}
