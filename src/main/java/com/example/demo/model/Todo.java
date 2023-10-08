package com.example.demo.model;

import com.example.demo.entity.ToDoEntity;

public class Todo {
   private Long id;
   private String title;
   private String description;

   public static Todo toModel(ToDoEntity todo) {
      Todo model = new Todo();
      model.setId(todo.getId());
      model.setTitle(todo.getTitle());
      model.setDescription(todo.getDescription());
      return model;
   }

   public Todo() {
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

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
