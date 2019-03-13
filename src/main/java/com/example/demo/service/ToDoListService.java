package com.example.demo.service;

import com.example.demo.model.dto.TodoListPageResult;
import com.example.demo.model.dto.TodoListResult;

public interface ToDoListService {
    public boolean insertTodoData(String toDo, String refId) throws Exception;
    public TodoListResult getTodoData(int seq) throws Exception;
    public TodoListPageResult getTodoDataList(int position, int size) throws Exception;
    public boolean updateTodoData(int seq, String toDo, String endYn) throws Exception;
}