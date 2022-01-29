package bfe;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

public class Main { // ricordati di sistemare i tooltiptext ed i final e i null da replacare nei joptionpanes e sistema anche i msg nei joptionpanes

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					//FrameA frame = new FrameA();
					FrameB frame = new FrameB("mi4qQ", null, null, null, null, null, null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Errore nell'apertura dell'interfaccia\n" + e);
				}

			}
		});

	}

}