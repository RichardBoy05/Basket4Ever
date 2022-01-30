package bfe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

public class SelectedPlayerListener implements MouseListener {

	private FrameB frame;

	public SelectedPlayerListener(FrameB frame) {

		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {

			JTable table = (JTable) e.getSource();

			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();

			if (column == 1) {

				String player = table.getValueAt(row, column).toString();

				if (player.charAt(player.length() - 1) == ' ') {

					player = player.substring(0, player.length() - 1);

				}

				showPlayer(player);

			}

		}

	}

	private void showPlayer(String player) {

		frame.getPlayerName().setText(player);
		frame.getSearchBut().doClick();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		return;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		return;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		return;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		return;
	}

}
