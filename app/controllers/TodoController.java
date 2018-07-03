package controllers;

import com.google.inject.Inject;
import models.Todo;
import play.data.*;
import play.mvc.*;
import views.html.*;

import java.util.List;

public class TodoController extends Controller {
    private final Form<Todo> form;

    private final DynamicForm dynamicForm;

    @Inject
    public TodoController(FormFactory formFactory) {
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
            Todo.create(data);
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
            Todo.update(todo);
            return Results.redirect(routes.TodoController.index());
        } else {
            return Results.redirect(routes.TodoController.showedit(id));
        }
    }

    public Result check(Long id) {
        Todo todo = Todo.findById(id);
        if (todo != null) {
            Todo.invertCheck(todo);
            Todo.update(todo);
            return Results.redirect(routes.TodoController.index());
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