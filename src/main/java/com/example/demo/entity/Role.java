package com.example.demo.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "name")
   private String name;
}
