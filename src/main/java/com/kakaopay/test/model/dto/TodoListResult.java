package com.kakaopay.test.model.dto;

import com.kakaopay.test.model.entity.ToDoListData;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;

@Getter
@Setter
public class TodoListResult {
    private ToDoListData toDoListData;
    private JSONArray refIds;
}
