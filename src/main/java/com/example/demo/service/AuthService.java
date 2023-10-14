package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dtos.JwtRequest;
import com.example.demo.dtos.JwtResponse;
import com.example.demo.dtos.RegistrationUserDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.AppError;
import com.example.demo.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class AuthService {
   private UserService userService;
   private JwtTokenUtils jwtTokenUtils;
   private AuthenticationManager authenticationManager;

   public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
      try {
         authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
      } catch (BadCredentialsException e) {
         return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Invalid login or password"),
               HttpStatus.UNAUTHORIZED);
      }
      UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
      String token = jwtTokenUtils.generateToken(userDetails);
      return ResponseEntity.ok(new JwtResponse(token));
   }

   public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
      if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
         return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Passwords don't match"),
               HttpStatus.BAD_REQUEST);
      }
      if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
         return new ResponseEntity<>(
               new AppError(HttpStatus.BAD_REQUEST.value(), "User has been already existed"),
               HttpStatus.BAD_REQUEST);
      }
      User user = userService.createNewUser(registrationUserDto);
      return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail(), null));
   }
}
