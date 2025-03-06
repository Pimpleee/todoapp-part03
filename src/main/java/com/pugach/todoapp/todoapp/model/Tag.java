package com.pugach.todoapp.todoapp.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<TodoItem> todoItems = new HashSet<>();

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(Set<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }
}
