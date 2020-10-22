package controllers;

import play.mvc.*;
import javax.inject.*;
import services.LogService;
import logger.Logging;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {

	private final LogService _logService;
	
	 @Inject
	 public HomeController(LogService logService) {
		 this._logService = logService;
	 }
	
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    @With(Logging.class)
    public Result index() {
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
