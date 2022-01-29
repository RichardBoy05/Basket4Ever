package bfe;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;

public final class Utils {
	
	private final static Font DEFAULT_FONT = new Font("Arial", Font.ITALIC, 10);
	
	private Utils() {
		// this class cannot be instantiated
	}

	public static final String capitalizeString(String input) {

		String result = "";
		String[] parts = input.split(" ");

		for (String currentPart : parts) {

			result += currentPart.substring(0, 1).toUpperCase() + currentPart.substring(1).toLowerCase() + " ";

		}

		return result;

	}

	public static final void fixLabelFontSize(JLabel label) {
		
		String labelText = label.getText();
		
		int stringWidth = label.getFontMetrics(DEFAULT_FONT).stringWidth(labelText);
		int componentWidth = label.getWidth();

		double widthRatio = (double) componentWidth / (double) stringWidth;

		int newFontSize = (int) (DEFAULT_FONT.getSize() * widthRatio);
		int componentHeight = label.getHeight();

		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		label.setFont(new Font(DEFAULT_FONT.getName(), Font.BOLD, fontSizeToUse));
		
	}
	
	public final static int[] fixRowHeights (int tableHeight, JTable table) {
		
		int[] results = new int[2]; // 1) rowHeight 2) lastRowHeight

		int rows = table.getRowCount();
		
		int rowHeight = tableHeight / rows;
		int remainingSpace = tableHeight % rows;
		
		results[0] = rowHeight;
		results[1] = rowHeight + remainingSpace;
				
		return results;
			
	}
	
}
