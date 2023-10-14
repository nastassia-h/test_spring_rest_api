package com.example.demo.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
   @GetMapping("/unsecured")
   public String unsecuredData() {
      return "Unsecured data";
   }

   @GetMapping("/secured")
   public String securedData() {
      return "Secured data";
   }

   @GetMapping("/admin")
   public String adminData() {
      return "Admin data";
   }

   @GetMapping("/info")
   public String userData(Principal principal) {
      return principal.getName();
   }
}
