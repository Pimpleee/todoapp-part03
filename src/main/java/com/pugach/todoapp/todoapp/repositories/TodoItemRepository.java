package com.pugach.todoapp.todoapp.repositories;

import com.pugach.todoapp.todoapp.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}
