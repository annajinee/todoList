package com.kakaopay.demo.service.impl;

import com.kakaopay.demo.model.dto.TodoListPageResult;
import com.kakaopay.demo.model.entity.ToDoListData;
import com.kakaopay.demo.model.entity.TodoRefData;
import com.kakaopay.demo.service.ToDoListService;
import com.kakaopay.demo.model.dao.ToDoListRepo;
import com.kakaopay.demo.model.dao.ToDoRefRepo;
import com.kakaopay.demo.model.dto.PageInfo;
import com.kakaopay.demo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ToDoListServiceImpl implements ToDoListService {

    private final ToDoListRepo toDoListRepo;
    private final ToDoRefRepo toDoRefRepo;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepo toDoListRepo, ToDoRefRepo toDoRefRepo) {
        this.toDoListRepo = toDoListRepo;
        this.toDoRefRepo = toDoRefRepo;
    }

    @Override
    public boolean insertTodoData(String toDo, JSONArray refIdArry) throws Exception {
        try {
            ToDoListData toDoListData = new ToDoListData();
            toDoListData.setToDo(toDo);
            toDoListData.setRegDate(StringUtil.getCurrentDateTime());
            toDoListData.setModDate(StringUtil.getCurrentDateTime());
            toDoListData.setEndYn("N");
            toDoListRepo.save(toDoListData);

            if (refIdArry.size() > 0) {
                TodoRefData todoRefData = new TodoRefData();
                for (Object refId : refIdArry) {
                    todoRefData.setToDoId(toDoListRepo.getRowId());
                    todoRefData.setRefId(Integer.parseInt(String.valueOf(refId)));
                    todoRefData.setToDoYn("N");
                    toDoRefRepo.save(todoRefData);
                }
            }

        } catch (Exception ex) {
            log.error(ex.toString());
            throw ex;
        }
        return true;
    }

    @Override
    public TodoListPageResult getTodoDataList(int position, int size) throws Exception {
        TodoListPageResult todoListResult = new TodoListPageResult();
        try {
            Pageable pageable = new PageRequest(position, size, Sort.Direction.ASC, "rowId");
            Page<ToDoListData> page = toDoListRepo.findAll(pageable);
            todoListResult.setDataList(page.getContent());
            PageInfo pageInfo = new PageInfo();
            pageInfo.setTotalCount(page.getTotalElements());
            pageInfo.setTotalPage(page.getTotalPages());
            pageInfo.setPageNumber(page.getNumber());
            todoListResult.setPageInfo(pageInfo);
        } catch (Exception ex) {
            log.error(ex.toString());
            throw ex;
        }
        return todoListResult;
    }

    @Override
    public boolean updateTodoData(int seq, String toDo, String endYn) throws Exception {

        try {
            ToDoListData toDoListData = toDoListRepo.findByRowId(seq);
            if (toDoListData == null) {
                throw new NullPointerException("Not Found");
            }
            if (toDo != null) {
                toDoListData.setToDo(toDo);
            }
            if (endYn != null && endYn.equals("Y") && isCompleteToDoIds(seq)) {
                toDoListData.setEndYn(endYn);
            } else if (endYn != null && endYn.equals("Y") && !isCompleteToDoIds(seq)) {
                throw new UnsupportedOperationException("Not Complete referenced tasks");
            }
            toDoListData.setModDate(StringUtil.getCurrentDateTime());
            toDoListRepo.save(toDoListData);

        } catch (Exception ex) {
            log.error(ex.toString());
            throw ex;
        }
        return true;
    }

    private boolean isCompleteToDoIds(int refId) {

        List<TodoRefData> todoRefDataList = toDoRefRepo.findByRefId(refId);
        for (TodoRefData todoRefData : todoRefDataList) {
            if (todoRefData.getToDoYn().equals("N")) {
                return false;
            }
        }
        return true;
    }
}
