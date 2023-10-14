package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
   private final RoleRepository roleRepository;

   public Role getUserRole() {
      return roleRepository.findByName("ROLE_USER").get();
   }
}
