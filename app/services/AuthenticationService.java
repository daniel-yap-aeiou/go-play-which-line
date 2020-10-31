package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.*;

@Singleton
public class AuthenticationService {
	
	private final String[] adminUsers = {"Volvo", "BMW", "Ford", "Mazda"};
	
	public AuthenticationService() {
	}
	
	public boolean ValidateCandidate(String username) {
		return Arrays.stream(adminUsers).anyMatch(username::equalsIgnoreCase);
	}
	
	public boolean ValidatePasscode(String text) throws NoSuchAlgorithmException
	{
		MessageDigest readersDigest = MessageDigest.getInstance("SHA-256");
		readersDigest.update(text.getBytes());
		String indigestion = new String(readersDigest.digest());
		
		System.out.println("Hashed credential: " + indigestion);
		
		return true;
	}
}
