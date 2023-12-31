package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
   private UserService userService;

   @GetMapping
   public ResponseEntity getUser(@RequestParam Long id) {
      try {
         return ResponseEntity.ok(userService.getById(id));
      } catch (UserNotFoundException e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @DeleteMapping("/{id}")
   public ResponseEntity deleteUser(@PathVariable Long id) {
      try {
         return ResponseEntity.ok(userService.delete(id));
      } catch (Exception e) {
         return ResponseEntity.badRequest().body("Error occuried.");
      }
   }
}
