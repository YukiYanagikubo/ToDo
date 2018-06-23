package controllers;

import com.google.inject.Inject;
import models.Todo;
import play.data.*;
import play.mvc.*;
import views.html.*;

import java.util.List;

public class HomeController extends Controller {
    private final Form<Todo> form;

    private final DynamicForm dynamicForm;

    @Inject
    public HomeController(FormFactory formFactory) {
        form = formFactory.form(Todo.class);
        dynamicForm = formFactory.form();
    }
    public Result index() {
        return Results.ok(index.render(Todo.all(), form));
    }

    public Result create() {
        Form<Todo> f = form.bindFromRequest();
        if (!f.hasErrors()) {
            Todo data = f.get();
            data.save();
            return Results.ok(index.render(Todo.all(), form));
        } else {
            return Results.badRequest(index.render(Todo.all(), f));
        }
    }

    public Result showedit(Long id) {
        Todo todo = Todo.findById(id);
        if (todo != null) {
            Form<Todo> f = form.fill(todo);
            return Results.ok(showedit.render(todo, f));
        } else {
            return Results.badRequest(index.render(Todo.all(), form));
        }
    }

    public Result edit(Long id) {
        Todo todo = Todo.findById(id);
        Form<Todo> ft = form.bindFromRequest();
        if (todo != null) {
            if (ft.hasErrors()) {
                return Results.badRequest(showedit.render(todo, ft));
            }
            todo.text = ft.get().text;
            todo.deadline = ft.get().deadline;
            todo.update();
            return Results.redirect(routes.HomeController.index());
        } else {
            return Results.redirect(routes.HomeController.showedit(id));
        }
    }

    public Result check(Long id) {
        Todo todo = Todo.findById(id);
        if (todo != null) {
            todo.done = !todo.done;
            todo.update();
            return Results.redirect(routes.HomeController.index());
        } else {
            return Results.badRequest(index.render(Todo.all(), form));
        }
    }

    public Result showsearch() {
        String text = dynamicForm.bindFromRequest().get("text");
        List<Todo> todos = Todo.findBytextLikeIncomplete(text);
        return Results.ok(showsearch.render(todos, form));
    }
}