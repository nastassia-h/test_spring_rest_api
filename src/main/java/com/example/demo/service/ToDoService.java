package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.Todo;
import com.example.demo.entity.ToDoEntity;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ToDoService {
   @Autowired
   private ToDoRepository todoRepository;
   @Autowired
   private UserRepository userRepository;

   public Todo createTodo(ToDoEntity todo, Long userId) {
      UserEntity user = userRepository.findById(userId).get();
      todo.setUser(user);
      return Todo.toModel(todoRepository.save(todo));
   }

   public Todo complete(Long id) {
      ToDoEntity todo = todoRepository.findById(id).get();
      todo.setCompleted(!todo.getCompleted());
      return Todo.toModel(todoRepository.save(todo));
   }
}
