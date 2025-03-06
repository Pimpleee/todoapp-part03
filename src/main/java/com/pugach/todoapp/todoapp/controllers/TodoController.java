package com.pugach.todoapp.todoapp.controllers;

import com.pugach.todoapp.todoapp.model.Tag;
import com.pugach.todoapp.todoapp.model.TodoItem;
import com.pugach.todoapp.todoapp.repositories.TagRepository;
import com.pugach.todoapp.todoapp.repositories.TodoItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TodoController implements CommandLineRunner {

    private final TodoItemRepository todoItemRepository;
    private final TagRepository tagRepository;

    public TodoController(TodoItemRepository todoItemRepository, TagRepository tagRepository) {
        this.todoItemRepository = todoItemRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<TodoItem> todoItems = todoItemRepository.findAll();
        model.addAttribute("todoItems", todoItems);
        return "index";
    }

    @PostMapping("/addTodo")
    public String addTodoItem(@RequestParam String title) {
        TodoItem newItem = new TodoItem();
        newItem.setTitle(title);
        todoItemRepository.save(newItem);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
        todoItemRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/removeAll")
    public String removeAllTodoItems() {
        todoItemRepository.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/addTag")
    public String addTag(@RequestParam String tagName) {
        Tag tag = new Tag(tagName);
        tagRepository.save(tag);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTodoItem(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemRepository.findById(id).orElse(null);
        List<Tag> allTags = tagRepository.findAll();
        model.addAttribute("todoItem", todoItem);
        model.addAttribute("allTags", allTags);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @ModelAttribute TodoItem todoItem, @RequestParam List<Long> tagIds) {
        TodoItem existingItem = todoItemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setTitle(todoItem.getTitle());
            Set<Tag> tags = new HashSet<>(tagRepository.findAllById(tagIds));
            existingItem.setTags(tags);
            todoItemRepository.save(existingItem);
        }
        return "redirect:/";
    }

    @Override
    public void run(String... args) throws Exception {
        todoItemRepository.save(new TodoItem("Дэмо задача 1"));
        todoItemRepository.save(new TodoItem("Дэмо задача 2"));
    }
}