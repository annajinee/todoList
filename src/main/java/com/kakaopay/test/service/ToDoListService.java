package com.kakaopay.test.service;

import com.kakaopay.test.model.dto.TodoListPageResult;
import org.json.simple.JSONArray;

public interface ToDoListService {
    public boolean insertTodoData(String toDo, JSONArray refId) throws Exception;
    public TodoListPageResult getTodoDataList(int position, int size) throws Exception;
    public boolean updateTodoData(int seq, String toDo, String endYn) throws Exception;
}
