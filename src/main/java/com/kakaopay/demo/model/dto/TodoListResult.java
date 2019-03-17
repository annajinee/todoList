package com.kakaopay.demo.model.dto;

import com.kakaopay.demo.model.entity.ToDoListData;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;

@Getter
@Setter
public class TodoListResult {
    private ToDoListData toDoListData;
    private JSONArray refIds;
}
