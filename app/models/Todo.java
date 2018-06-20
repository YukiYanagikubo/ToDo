package models;


import io.ebean.*;
import io.ebean.annotation.CreatedTimestamp;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.*;

@Entity
public class Todo extends Model {

    public static Finder<Long, Todo> find = new Finder<>(Todo.class);

    @Id
    public Long id;

    @Constraints.Required(message = "必須項目です。")
    @Constraints.MaxLength(message = "30文字を越えています。", value = 31)
    public String text;

    @CreatedTimestamp
    public Date postdate;

    public Date deadline;

    public boolean done = false;

    public static List<Todo> all() {
        return Todo.find.all();
    }

}