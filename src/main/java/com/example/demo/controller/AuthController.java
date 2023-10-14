package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.JwtRequest;
import com.example.demo.dtos.RegistrationUserDto;
import com.example.demo.service.AuthService;

@RestController
public class AuthController {
   private AuthService authService;

   @PostMapping("/auth")
   public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
      return authService.createAuthToken(authRequest);
   }

   @PostMapping("/registration")
   public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
      return authService.createNewUser(registrationUserDto);
   }
}
