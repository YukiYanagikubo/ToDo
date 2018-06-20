package controllers;

import com.google.inject.Inject;
import models.Todo;
import play.data.*;
import play.mvc.*;
import views.html.*;

import java.util.List;

public class HomeController extends Controller {
    private final Form<Todo> form;

    @Inject
    public HomeController(FormFactory formFactory) {
        form = formFactory.form(Todo.class);
    }
    public Result index() {
        List<Todo> todos = Todo.all();
        return Results.ok(index.render(todos, form));
    }

    public Result create() {
        Form<Todo> f = form.bindFromRequest();
        if (!f.hasErrors()) {
            Todo data = f.get();
            data.save();
            List<Todo> todos = Todo.all();
            return Results.ok(index.render(todos, f));
        } else {
            List<Todo> todos = Todo.all();
            return Results.badRequest(index.render(todos, form));
        }
    }

    public Result edit() {
        return Results.ok(edit.render());
    }

    public Result search() {
        return Results.ok(search.render());
    }
}