package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
//import com.example.demo.model.Todo;
import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ToDoService {
   @Autowired
   private TodoRepository todoRepository;
   @Autowired
   private UserRepository userRepository;

   public Todo createTodo(Todo todo, Long userId) {
      User user = userRepository.findById(userId).get();
      todo.setUser(user);
      return todoRepository.save(todo);
   }

   public Todo complete(Long id) {
      Todo todo = todoRepository.findById(id).get();
      todo.setCompleted(!todo.getCompleted());
      return todoRepository.save(todo);
   }
}
