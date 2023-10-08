package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
      if (userRepository.findByUsername(user.getUsername()) != null) {
         throw new UserAlreadyExistsException("user already exists.");
      }
      return userRepository.save(user);
   }

   public User getById(Long id) throws UserNotFoundException {
      UserEntity user = userRepository.findById(id).get();
      if (user == null) {
         throw new UserNotFoundException("user not found.");
      }
      return User.toModel(user);
   }

   public Long delete(Long id) {
      userRepository.deleteById(id);
      return id;
   }
}
