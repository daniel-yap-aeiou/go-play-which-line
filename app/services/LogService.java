package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.inject.*;

@Singleton
public class LogService {
	public LogService() {
	}
	
	public void PrintMesssage() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss");
		LocalDateTime now = java.time.LocalDateTime.now();
		System.out.println("[" + dtf.format(now) + "] Test 123");	
	}
}
