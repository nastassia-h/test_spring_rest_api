package com.example.demo.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.RegistrationUserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;
   private PasswordEncoder passwordEncoder;

   public Optional<User> findByUsername(String username) {
      return userRepository.findByUsername(username);
   }

   public User getById(Long id) throws UserNotFoundException {
      User user = userRepository.findById(id).get();
      if (user == null) {
         throw new UserNotFoundException("user not found.");
      }
      return user;
   }

   public Long delete(Long id) {
      userRepository.deleteById(id);
      return id;
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
            String.format("User '%s' not found", username)));
      return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                  .collect(Collectors.toList()));
   }

   public User createNewUser(RegistrationUserDto registrationUserDto) {
      User user = new User();
      user.setUsername(registrationUserDto.getUsername());
      user.setEmail(registrationUserDto.getEmail());
      user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
      return userRepository.save(user);
   }
}
