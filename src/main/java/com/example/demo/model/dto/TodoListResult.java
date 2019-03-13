package com.example.demo.model.dto;

import com.example.demo.model.entity.ToDoListData;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;

@Getter
@Setter
public class TodoListResult {
    private ToDoListData toDoListData;
    private JSONArray refIds;
}
