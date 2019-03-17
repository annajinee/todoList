package com.kakaopay.demo.model.dao;

import com.kakaopay.demo.model.entity.TodoRefData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRefRepo extends JpaRepository<TodoRefData, Integer> {
    public List<TodoRefData> findByRefId(int refId);
}
