package com.pugach.todoapp.todoapp.repositories;

import com.pugach.todoapp.todoapp.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
