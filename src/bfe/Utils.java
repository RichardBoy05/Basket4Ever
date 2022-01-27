package bfe;

import java.awt.Font;

import javax.swing.JLabel;

public final class Utils {
	
	private final static Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);

	private Utils() {
		// this class cannot be intantiated
	}

	public static final String capitalizeString(String input) {

		String result = "";
		String[] parts = input.split(" ");

		for (String currentPart : parts) {

			result += currentPart.substring(0, 1).toUpperCase() + currentPart.substring(1).toLowerCase() + " ";

		}

		return result;

	}

	public static final void fixLabelFontSize(JLabel label, boolean firstExecuted) {
		
		Font initialLabelFont = firstExecuted == true ? DEFAULT_FONT : label.getFont();
		
		String initialLabelText = label.getText();

		int stringWidth = label.getFontMetrics(initialLabelFont).stringWidth(initialLabelText);
		int componentWidth = label.getWidth();

		double widthRatio = (double) componentWidth / (double) stringWidth;

		int newFontSize = (int) (initialLabelFont.getSize() * widthRatio);
		int componentHeight = label.getHeight();

		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		Font result = new Font(initialLabelFont.getName(), Font.BOLD, fontSizeToUse);
		label.setFont(result);

	}
	
}
