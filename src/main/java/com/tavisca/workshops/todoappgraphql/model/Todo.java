package com.tavisca.workshops.todoappgraphql.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    private int id;
    private String todoname;

    public Todo() {
    }

    public Todo(int id, String todoname) {
        this.id = id;
        this.todoname = todoname;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", todoname='" + todoname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getTodoname() {
        return todoname;
    }
}
