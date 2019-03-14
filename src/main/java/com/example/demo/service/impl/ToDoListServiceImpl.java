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

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class ToDoListServiceImpl extends CommonService implements ToDoListService {

    @Autowired
    private ToDoListRepo toDoListRepo;
    @Autowired
    private ToDoRefRepo toDoRefRepo;

    @Override
    public boolean insertTodoData(String toDo, JSONArray refIdArry) throws Exception {

        try {
            ToDoListData toDoListData = new ToDoListData();
            toDoListData.setToDo(toDo);
            toDoListData.setRegDate(StringUtil.getCurrentDateTime());
            toDoListData.setModDate(StringUtil.getCurrentDateTime());
            toDoListData.setEndYn("N");
            toDoListRepo.save(toDoListData);

            if(refIdArry.size()>0){
                TodoRefData todoRefData = new TodoRefData();
                for(int i=0; i<refIdArry.size(); i++){
                    todoRefData.setToDoId(toDoListRepo.getRowId());
                    todoRefData.setRefId(Integer.parseInt(String.valueOf(refIdArry.get(i))));
                    todoRefData.setToDoYn("N");
                    toDoRefRepo.save(todoRefData);
                }
            }

        } catch (Exception ex) {
            logger.error(ex.toString());
            throw ex;
        }
        return true;
    }

    @Override
    public ToDoListData getTodoData(int seq) throws Exception {

        ToDoListData toDoListData = new ToDoListData();
        try {
            toDoListData = toDoListRepo.findByRowId(seq);
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw ex;
        }
        return toDoListData;
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
