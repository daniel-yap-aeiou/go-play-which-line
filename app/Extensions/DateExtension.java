package Extensions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateExtension {

	public static String DateInLongFormat() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, yyyy-MMM-dd HH:mm:ss");
		LocalDateTime now = java.time.LocalDateTime.now();
		return dtf.format(now);
	}
}
