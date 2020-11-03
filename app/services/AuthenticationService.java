package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.*;

import Extensions.IntergerExtension;

@Singleton
public class AuthenticationService {
	
	private final String[] adminUsers = {"Volvo", "BMW", "Ford", "Mazda"};
	private final Integer[] validTicketNumbers = {1234, 5678, 4456, 5525};
	private final String[] tokens  = {
		"h>c);,73k)Ukad(%,!fb8PnU>e/TqM#7k@;4'K]\\~,uKZ\";]\"vJ:y$!",
		"jFEhEJEAs.5nx9}2?@^[vRL9V4'3Swu})E@-M(#z~;fuBpY#jB_;(/K",
		"YRAhu+jA@FvW6XhtbqU3^>2\\M.-F]bp5vA&JKM,t_zjv\"7!~Us.TdhX",
		"62q;y6XXw\"[da:meAa2W'9:4/6,-gHd6cS@{=$jBrhesPHU&fsL&[_-",
		"rK&B9M?pVGRf+B6zUWM'~7c+`pHs3C3'u,\"+(,6)Ms.Fu^(~j)8wb?=",
		"3<K6m}W!44CQCfETK$!q]'}}qavLMu%KD9w;F!&~*sWErx8pQQt'S$h",
		"VEZ]2r?s%7$.-Zg7ZT[uzC+s?qSnrB!'L2^ne9\\Vg8+KW+zx:L[\"L6e",
		"5k*QeBxcQ8\"m2e\"B9g[=WPeu!Z&D=SprG&k<{ttR2y\\g$xda\\xbb2\":"
	};
	
	public AuthenticationService() {
	}
	
	public boolean ValidateCandidate(String username) {
		if (username == null) return false;
		
		return Arrays.stream(adminUsers).anyMatch(username::equalsIgnoreCase);
	}
	
	public boolean ValidatePasscode(String text) throws NoSuchAlgorithmException
	{
		if (text == null) return false;
		
		MessageDigest readersDigest = MessageDigest.getInstance("SHA-256");
		readersDigest.update(text.getBytes());
		String indigestion = new String(readersDigest.digest());
		
		System.out.println("Hashed credential: " + indigestion);
		
		return true;
	}
	
	public boolean ValidateTicketNumber(Integer ticketNumber)
	{
		return Arrays.stream(validTicketNumbers).anyMatch(ticketNumber::equals);
	}
	
	public boolean ValidateToken(String token) {
		if (token == null) return false;
		
		return Arrays.stream(tokens).anyMatch(token::equalsIgnoreCase);
	}
	
	public String FinalTicketNumber(String text) {
		if (text == null) return "danny.yap";
		
		boolean ticketNumberValid = ValidateTicketNumber(IntergerExtension.TryParseInteger(text));
	    
	    if (ticketNumberValid) {
	    	text += "-puss";
	    } else {
	    	text += "-unpuss";
	    }
	    
	    return text;
	}
}
