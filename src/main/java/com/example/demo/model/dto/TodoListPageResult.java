package com.example.demo.model.dto;

import com.example.demo.model.entity.ToDoListData;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class TodoListPageResult {
    private List<ToDoListData> dataList;
    private PageInfo pageInfo;
}
