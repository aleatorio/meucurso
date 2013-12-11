package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render(""));
    }
>>>>>>> 8424327e55fb0ea602db53f994b582653074745e
}
