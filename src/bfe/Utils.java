package bfe;

public final class Utils {

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

}
