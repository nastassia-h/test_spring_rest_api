package com.example.demo.model;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.UserEntity;

public class User {
   private Long id;
   private String username;
   private List<Todo> todos;

   public static User toModel(UserEntity user) {
      User model = new User();
      model.setId(user.getId());
      model.setUsername(user.getUsername());
      model.setTodos(user.getTodos().stream().map(Todo::toModel).collect(Collectors.toList()));
      return model;
   }

   public User() {

   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public List<Todo> getTodos() {
      return todos;
   }

   public void setTodos(List<Todo> todos) {
      this.todos = todos;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

}
