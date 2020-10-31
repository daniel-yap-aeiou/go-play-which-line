package controllers;

import play.mvc.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.inject.*;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.data.DynamicForm;
import play.data.FormFactory;
import services.AuthenticationService;
import services.LogService;
import logger.Logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private final LogService _logService;
	private final MailerClient _mailerClient;
	private final FormFactory _formFactory;
	private final AuthenticationService _authenticationService;
	
	 @Inject
	 public HomeController(LogService logService,
			 MailerClient mailerClient,
			 FormFactory formFactory,
			 AuthenticationService authenticationService
			 )
	 {
		 this._logService = logService;
		 this._mailerClient = mailerClient;
		 this._formFactory = formFactory;
		 this._authenticationService = authenticationService;
	 }
	
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    @With(Logging.class)
    public Result index() {
    	String cid = "1234";
        Email email = new Email()
          .setSubject("Simple email")
          .setFrom("xxx@gmail.com")
          .addTo("yyy@gmail.com")
          // sends text, HTML or both...
          .setBodyText("A text message")
          .setBodyHtml("<html><body><p>An <b>html</b> message with cid <img src=\"cid:" + cid + "\"></p></body></html>");
        this._mailerClient.send(email);
    	
        return ok(views.html.index.render());
    }
    
    @With(Logging.class)
    public Result todo() {
    	return ok(views.html.todolist.render());
    }

    @With(Logging.class)
    public Result bikey() {
    	return ok(views.html.bikey.render());
    }
    
    @With(Logging.class)
    public Result boardingpass() {
    	return ok(views.html.boardingpass.render());
    }
    
    @With(Logging.class)
    public Result login() {
    	return ok(views.html.login.render());
    }
    
    @With(Logging.class)
    public Result authenticate(Http.Request request) {
    	DynamicForm dynamicForm = this._formFactory.form().bindFromRequest(request);    
    	
    	String username = dynamicForm.get("username");
    	String password = dynamicForm.get("password");
    	String ticketNo = dynamicForm.get("number");
    	
	    System.out.println("Username is: " + username);
	    System.out.println("Password is: " + password);
	    System.out.println("Ticket no. is: " + ticketNo);
	    
	    boolean candidateValid = this._authenticationService.ValidateCandidate(username);
	    boolean passwordValid = false;
	    try {
	    	passwordValid = this._authenticationService.ValidatePasscode(password);
	    }
	    catch (NoSuchAlgorithmException ex)
	    {
	    	System.out.println(ex);
	    }
	    
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, yyyy-MMM-dd HH:mm:ss");
		LocalDateTime now = java.time.LocalDateTime.now();	
	    
	    HashMap<String, String> mymap = new HashMap<String, String>();
	    mymap.put("username", username);
	    mymap.put("candidateValid", Boolean.toString(candidateValid));
	    mymap.put("ticketNo", ticketNo);
	    mymap.put("date", dtf.format(now));
	    
	    return ok(views.html.authenticate.render(mymap));
    }
}
