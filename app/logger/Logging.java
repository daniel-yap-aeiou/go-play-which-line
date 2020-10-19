package logger;

import play.mvc.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionStage;

public class Logging extends Action.Simple {
	public CompletionStage<Result> call(Http.Request req) {
		long now = System.currentTimeMillis();
		try {
			return delegate.call(req);
		} finally {
			long elapsedTime = System.currentTimeMillis() - now;
			String prefix = req.method() + ": " + req.path() + " | IP Address: " + req.remoteAddress();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss");
			LocalDateTime noww = java.time.LocalDateTime.now();
			
			System.out.println("[" + dtf.format(noww) + "] [" + prefix + "] elapsed_time {" + elapsedTime + "} | " + req.uri());
		}
	}
}