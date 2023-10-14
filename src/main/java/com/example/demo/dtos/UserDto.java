package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
   private Long id;
   private String username;
   private String email;
   private Collection<TodoDto> todos;
}
