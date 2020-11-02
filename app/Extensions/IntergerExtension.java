package Extensions;

public final class IntergerExtension {
	public static Integer TryParseInteger(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
}
