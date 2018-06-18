package controllers;

import play.mvc.*;
import views.html.*;

public class HomeController extends Controller {

    public Result index() {
        return Results.ok(index.render("Your new application is ready."));
    }

    public Result edit() {
        return Results.ok(edit.render());
    }
}