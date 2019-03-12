package com.example.demo.service;

import com.example.demo.model.entity.ToDoListData;

public interface ToDoListService {
    public boolean insertTodoData(String work) throws Exception;
    public ToDoListData getTodoData(int seq) throws Exception;
}
