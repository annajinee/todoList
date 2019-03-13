package com.example.demo.service.impl;

import com.example.demo.model.dao.ToDoListRepo;
import com.example.demo.model.dao.ToDoRefRepo;
import com.example.demo.model.dto.PageInfo;
import com.example.demo.model.dto.TodoListPageResult;
import com.example.demo.model.dto.TodoListResult;
import com.example.demo.model.entity.ToDoListData;
import com.example.demo.model.entity.TodoRefData;
import com.example.demo.service.CommonService;
import com.example.demo.service.ToDoListService;
import com.example.demo.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ToDoListServiceImpl extends CommonService implements ToDoListService {

    private final ToDoListRepo toDoListRepo;
    private final ToDoRefRepo toDoRefRepo;

    @Autowired
    public ToDoListServiceImpl(ToDoRefRepo toDoRefRepo, ToDoListRepo toDoListRepo) {
        this.toDoRefRepo = toDoRefRepo;
        this.toDoListRepo = toDoListRepo;
    }


    @Override
    public boolean insertTodoData(String toDo, String refId) throws Exception {

        try {
            ToDoListData toDoListData = new ToDoListData();
            toDoListData.setToDo(toDo);
            toDoListData.setRegDate(StringUtil.getCurrentDateTime());
            toDoListData.setModDate(StringUtil.getCurrentDateTime());
            toDoListData.setEndYn("N");
            toDoListRepo.save(toDoListData);
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw ex;
        }
        return true;
    }

    @Override
    public TodoListResult getTodoData(int seq) throws Exception {

        TodoListResult todoListResult = new TodoListResult();
        try {
            ToDoListData toDoListData = toDoListRepo.findByRowId(seq);
            todoListResult.setToDoListData(toDoListData);
            todoListResult.setRefIds(getTodoRefIds(toDoListData.getRowId()));
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw ex;
        }
        return todoListResult;
    }

    @Override
    public TodoListPageResult getTodoDataList(int position, int size) throws Exception {
        TodoListPageResult todoListResult = new TodoListPageResult();
        try {
            Pageable pageable = new PageRequest(position, size, Sort.Direction.DESC, "rowId");
            Page<ToDoListData> page = toDoListRepo.findAll(pageable);
            todoListResult.setDataList(page.getContent());
            PageInfo pageInfo = new PageInfo();
            pageInfo.setTotalCount(page.getTotalElements());
            pageInfo.setTotalPage(page.getTotalPages());
            todoListResult.setPageInfo(pageInfo);
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw ex;
        }
        return todoListResult;

    }

    @Override
    public boolean updateTodoData(int seq, String toDo, String endYn) throws Exception {

        try {
            ToDoListData toDoListData = toDoListRepo.findByRowId(seq);
            if (toDoListData != null) {
                if(!toDo.equals("")){
                    toDoListData.setToDo(toDo);
                }
                if(endYn.equals("Y") && isCompleteTodoIds(seq)){
                    toDoListData.setEndYn(endYn);
                }
                toDoListData.setModDate(StringUtil.getCurrentDateTime());
                toDoListRepo.save(toDoListData);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw ex;
        }
        return true;
    }


    private JSONArray getTodoRefIds(int toDoId) {
        JSONArray resArray = new JSONArray();
        try {
            List<TodoRefData> todoRefDataList = toDoRefRepo.findByTodoId(toDoId);
            for (TodoRefData todoRefData : todoRefDataList) {
                resArray.add(todoRefData.getRefId());
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw ex;
        }
        return resArray;
    }

    private boolean isCompleteTodoIds(int refId){

        List<TodoRefData> todoRefDataList = toDoRefRepo.findByRefId(refId);
        for(TodoRefData todoRefData : todoRefDataList){
            if(todoRefData.getToDoYn().equals("N")){
                return false;
            }
        }
        return true;
    }
}
