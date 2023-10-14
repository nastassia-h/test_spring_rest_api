package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Todo;
import com.example.demo.service.ToDoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
   @Autowired
   private ToDoService todoService;

   @PostMapping
   public ResponseEntity createTodo(@RequestBody Todo todo,
         @RequestParam Long userId) {
      try {
         return ResponseEntity.ok(todoService.createTodo(todo, userId));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body("Error occuried.");
      }
   }

   @PutMapping
   public ResponseEntity completeTodo(@RequestParam Long id) {
      try {
         return ResponseEntity.ok(todoService.complete(id));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body("Error occuried.");
      }
   }
}
