package controllers;

import play.mvc.*;
import views.html.index;

public class HomeController extends Controller {

    public Result index() {
        return Results.ok(index.render("Your new application is ready."));
    }
}