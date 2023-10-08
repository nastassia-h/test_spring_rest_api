package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.ToDoEntity;

public interface ToDoRepository extends CrudRepository<ToDoEntity, Long> {

}
