package models;


import io.ebean.*;
import io.ebean.annotation.CreatedTimestamp;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.*;

@Entity
public class Todo extends Model {

    private static final Finder<Long, Todo> find = new Finder<>(Todo.class);

    @Id
    public Long id;

    @Constraints.Required(message = "必須項目です。")
    @Constraints.MaxLength(message = "30文字を越えています。", value = 30)
    public String text;

    @CreatedTimestamp
    public Date postdate;

    @Constraints.Required(message = "必須項目です。")
    public Date deadline;

    public boolean done = false;

    public static void create(Todo todo) {
        todo.save();
    }

    public static void update(Todo todo) {
        todo.update();
    }

    public static void invertCheck(Todo todo) {
        todo.done = !todo.done;
    }

    public static List<Todo> all() {
        return Todo.find.all();
    }

    public static Todo findById(Long id) {
        return Todo.find.byId(id);
    }

    public static List<Todo> findBytextLikeIncomplete(String text) {
        List<Todo> datas = Todo.find.query().where().eq("done", false).ilike("text", "%" + text + "%").orderBy("postdate desc").findList();
        return datas;
    }
}