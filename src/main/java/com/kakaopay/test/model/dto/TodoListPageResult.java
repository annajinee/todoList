package com.kakaopay.test.model.dto;

import com.kakaopay.test.model.entity.ToDoListData;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class TodoListPageResult {
    private List<ToDoListData> dataList;
    private PageInfo pageInfo;
}
