package controllers;

import com.google.inject.Inject;
import models.Todo;
import play.data.*;
import play.mvc.*;
import views.html.*;

import java.util.*;

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

    public Result showedit(Long id) {
        Todo todo = Todo.findById(id);
        if (todo != null) {
            Form<Todo> f = form.fill(todo);
            return Results.ok(views.html.showedit.render(todo, f));
        } else {
            List<Todo> todos = Todo.all();
            return Results.badRequest(index.render(todos, form));
        }
    }

    public Result edit(Long id) {
        Todo todo = Todo.findById(id);
        Form<Todo> ft = form.bindFromRequest();
        if (todo != null) {
            if (ft.hasErrors()) {
                return Results.badRequest(views.html.showedit.render(todo, ft));
            }
            String text = ft.get().text;
            Date deadline = ft.get().deadline;
            todo.text = text;
            todo.deadline = deadline;
            todo.update();
            return Results.redirect(routes.HomeController.index());
        } else {
            return Results.badRequest(views.html.showedit.render(todo, ft));
        }
    }

    public Result search() {
        return Results.ok(search.render());
    }
}