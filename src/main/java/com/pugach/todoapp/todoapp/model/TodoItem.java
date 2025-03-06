package com.pugach.todoapp.todoapp.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TodoItem {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToMany
    @JoinTable(
            name = "todoitem_tag",
            joinColumns = @JoinColumn(name = "todoitem_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public TodoItem() {
    }

    public TodoItem(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
