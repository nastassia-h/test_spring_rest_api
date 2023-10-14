package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "todos")
public class Todo {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @Column(name = "title")
   private String title;

   @Column(name = "description")
   private String description;

   @Column(name = "completed")
   private Boolean completed;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
}
