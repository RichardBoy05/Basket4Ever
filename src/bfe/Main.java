package bfe;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class Main { // ricordati di sistemare i tolltiptext ed i final/*

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					FrameA frame = new FrameA();
					//FrameB frame = new FrameB(null, null, null, null, null, null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Errore nell'apertura dell'interfaccia\n" + e);
				}

			}
		});

	}

}