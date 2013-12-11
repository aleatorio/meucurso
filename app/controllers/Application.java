package controllers;

import models.Planejador;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static final Planejador planejador = new Planejador();

	public static Result index() {
		return ok(views.html.index.render(planejador.getPeriodos()));
	}
}
