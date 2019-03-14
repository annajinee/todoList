package com.example.demo.model.dao;

import com.example.demo.model.entity.TodoRefData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRefRepo extends JpaRepository<TodoRefData, Integer> {
    public List<TodoRefData> findByToDoId(int toDoId);
    public List<TodoRefData> findByRefId(int refId);

}
