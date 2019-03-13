package com.example.demo.model.dao;

import com.example.demo.model.entity.ToDoListData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepo extends JpaRepository<ToDoListData, Integer> {
    public ToDoListData findByRowId(int rowId);
}
