package bfe;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ListHandler implements ItemListener {

	private FrameA frame;
	private StringBuilder builder = new StringBuilder();

	public ListHandler(FrameA frame) {
		this.frame = frame;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (e.getSource().equals(frame.getGendercb()) && e.getStateChange() == ItemEvent.SELECTED) {

			if (frame.getGendercb().getSelectedIndex() == 1) {
				frame.getYearcb().removeAllItems();
				for (String temp : frame.getYearlsf()) {
					frame.getYearcb().addItem(temp);
				}
			}

			if (frame.getGendercb().getSelectedIndex() == 0) {
				frame.getYearcb().removeAllItems();
				for (String temp : frame.getYearlsm()) {
					frame.getYearcb().addItem(temp);
				}
			}
		}

		if (e.getSource().equals(frame.getYearcb()) && e.getStateChange() == ItemEvent.SELECTED) {

			switch (frame.getYearcb().getSelectedItem().toString()) {

			case "C Gold" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Regionale");
			}
			case "C Silver" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Regionale");
			}
			case "Serie D" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Regionale");
			}
			case "Promozione" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Regionale");
			}
			case "Prima Divisione" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Regionale");
			}
			case "Seconda Divisione" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Regionale");
			}
			case "U20" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Silver");
			}
			case "U19" -> {
				frame.getLevelcb().removeAllItems();
				if (frame.getGendercb().getSelectedIndex() == 0) {
					frame.getLevelcb().addItem("Eccellenza");
					frame.getLevelcb().addItem("Gold");
					frame.getLevelcb().addItem("Silver");
				} else {
					frame.getLevelcb().addItem("Regionale");
				}
			}
			case "U17" -> {
				frame.getLevelcb().removeAllItems();
				if (frame.getGendercb().getSelectedIndex() == 0) {
					frame.getLevelcb().addItem("Eccellenza");
					frame.getLevelcb().addItem("Gold");
					frame.getLevelcb().addItem("Silver");
				} else {
					frame.getLevelcb().addItem("Elité");
					frame.getLevelcb().addItem("Regionale");
				}
			}
			case "U16" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Gold");
				frame.getLevelcb().addItem("Silver");
			}
			case "U15" -> {
				frame.getLevelcb().removeAllItems();
				if (frame.getGendercb().getSelectedIndex() == 0) {
					frame.getLevelcb().addItem("Eccellenza");
					frame.getLevelcb().addItem("Gold");
					frame.getLevelcb().addItem("Silver");
				} else {
					frame.getLevelcb().addItem("Regionale");
				}
			}
			case "U14" -> {
				frame.getLevelcb().removeAllItems();
				if (frame.getGendercb().getSelectedIndex() == 0) {
					frame.getLevelcb().addItem("Elité");
					frame.getLevelcb().addItem("Gold");
					frame.getLevelcb().addItem("Silver");
				} else {
					frame.getLevelcb().addItem("Regionale");
				}
			}
			case "U13" -> {
				frame.getLevelcb().removeAllItems();
				if (frame.getGendercb().getSelectedIndex() == 0) {
					frame.getLevelcb().addItem("Elité");
					frame.getLevelcb().addItem("Gold");
					frame.getLevelcb().addItem("Silver");
				} else {
					frame.getLevelcb().addItem("Regionale");
				}
			}
			case "Serie B" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Regionale");
			}

			case "Serie C" -> {
				frame.getLevelcb().removeAllItems();
				frame.getLevelcb().addItem("Regionale");
			}

			default -> JOptionPane.showMessageDialog(null,
					"Errore imprevisto nella classificazione dei gironi. Effettua una segnalazione!");

			}

		}

		if (e.getSource().equals(frame.getLevelcb()) && e.getStateChange() == ItemEvent.SELECTED) {

			char first = BuildUrl.getFirstChar(frame.getGendercb().getSelectedItem().toString());
			char second = BuildUrl.getSecondChar(frame.getYearcb().getSelectedItem().toString(),
					frame.getGendercb().getSelectedItem().toString());
			char third = BuildUrl.getThirdChar(frame.getLevelcb().getSelectedItem().toString());
			char fourth = BuildUrl.getFourthChar(frame.getFasecb().getSelectedItem().toString());

			builder.append(first);
			builder.append(second);
			builder.append(third);
			builder.append(fourth);

			frame.getGroupcb().removeAllItems();

			String code = builder.toString();

			if (code.contains("@")) {
				JOptionPane.showMessageDialog(null,
						"Errore imprevisto nella classificazione dei gironi. Effettua una segnalazione!");
			}

			builder.delete(0, builder.length());

			String line;
			String[] arrayline = new String[5];

			try {

				BufferedReader br = new BufferedReader(new FileReader("./database/groups.txt"));

				while ((line = br.readLine()) != null) {

					arrayline = line.split(";");
					if (arrayline[0].substring(0, arrayline[0].length() - 1).equals(code)) {
						frame.getGroupcb().addItem(arrayline[4]);
					}

				}

				br.close();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

		}

		if (e.getSource().equals(frame.getFasecb()) && e.getStateChange() == ItemEvent.SELECTED) {

			if (frame.getFasecb().getSelectedIndex() == 1) {
				frame.getGroupcb().removeAllItems();
				frame.getGroupcb().addItem("Nessun girone disponibile!");
			} else {

				String[] before_changing = new String[frame.getLevelcb().getItemCount()];
				int before_selection = frame.getLevelcb().getSelectedIndex();

				for (int i = 0; i < frame.getLevelcb().getItemCount(); i++) {
					before_changing[i] = frame.getLevelcb().getItemAt(i);
				}

				frame.getLevelcb().removeAllItems();

				for (String temp : before_changing) {
					frame.getLevelcb().addItem(temp);
				}

				frame.getLevelcb().setSelectedIndex(before_selection);
			}

		}
	}

}
