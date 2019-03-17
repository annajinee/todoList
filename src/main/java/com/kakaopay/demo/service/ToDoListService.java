package com.kakaopay.demo.service;

import com.kakaopay.demo.model.dto.TodoListPageResult;
import org.json.simple.JSONArray;

public interface ToDoListService {
    public boolean insertTodoData(String toDo, JSONArray refId) throws Exception;
    public TodoListPageResult getTodoDataList(int position, int size) throws Exception;
    public boolean updateTodoData(int seq, String toDo, String endYn) throws Exception;
}
