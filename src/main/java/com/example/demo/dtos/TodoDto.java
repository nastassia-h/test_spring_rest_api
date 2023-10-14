package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoDto {
   private Long id;
   private String title;
   private String description;
   private Boolean completed;
}
