package bfe;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

public class Main { // ricordati di sistemare i tooltiptext ed i final e i null da replacare nei
					// joptionpanes e sistema anche i msg nei joptionpanes ed aggiungi la
					// possibilità di loaddare un salvataggio direttamente dalla schermata iniziale
					// e aggiungi check se c'è il file di configurazione il database, le resources e
					// tutti i file necessari e se lo scgermo è gramde a suffiienza

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					FrameA frame = new FrameA();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Errore nell'apertura dell'interfaccia\n" + e);
				}

			}
		});

	}

}