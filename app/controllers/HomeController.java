package controllers;

import play.mvc.*;
import javax.inject.*;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import services.LogService;
import logger.Logging;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {

	private final LogService _logService;
	private final MailerClient _mailerClient;
	
	 @Inject
	 public HomeController(LogService logService,
			 MailerClient mailerClient
			 )
	 {
		 this._logService = logService;
		 this._mailerClient = mailerClient;
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
}
