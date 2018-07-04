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

    public static void create(String text, Date deadline) {
        Todo todo = new Todo();
        todo.text = text;
        todo.deadline = deadline;
        todo.save();
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

    public void invertCheck(Boolean done) {
        this.done = !done;
        update();
    }

    public void updateWith(String text, Date deadline) {
        this.text = text;
        this.deadline = deadline;
        update();
    }
}