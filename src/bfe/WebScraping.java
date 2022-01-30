package bfe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class WebScraping implements ActionListener {

	private FrameA frame;

	public WebScraping(FrameA frame) {

		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (frame.getGroupcb().getSelectedItem().toString().equals("Nessun girone disponibile!")) {
			JOptionPane.showMessageDialog(null, "Il girone selezionato non è valido!");
			return;
		}

		String gender = frame.getGendercb().getSelectedItem().toString();
		String year = frame.getYearcb().getSelectedItem().toString();
		String level = frame.getLevelcb().getSelectedItem().toString();
		String fase = frame.getFasecb().getSelectedItem().toString();
		String ids[] = BuildUrl.getIds(gender, year, level, fase, frame.getGroupcb());// index0 = idstart; index1 = idend;																				// index2 = idgro
			
		SecondThread stats = new SecondThread(gender, ids, frame.getBar(), frame.getProgressLabel(), this.frame);
		stats.execute();
		
		frame.getGendercb().setEnabled(false);
		frame.getYearcb().setEnabled(false);
		frame.getLevelcb().setEnabled(false);
		frame.getFasecb().setEnabled(false);
		frame.getGroupcb().setEnabled(false);
		
		frame.getGoBut().setEnabled(false);
		frame.getLoadBut().setEnabled(false);
		

	}
}
