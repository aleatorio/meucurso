package controllers;

import models.Planejador;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	private static Planejador planejador = new Planejador();

	public static Result index() {
		return ok(index.render(planejador.getPeriodos()));
	}

}
