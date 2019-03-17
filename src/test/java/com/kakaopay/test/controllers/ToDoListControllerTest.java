package com.kakaopay.test.controllers;

import com.kakaopay.test.MockMvcTest;
import com.kakaopay.test.model.dto.ToDoAddPayload;
import com.kakaopay.test.model.dto.ToDoUpdatePayload;
import com.kakaopay.test.model.dto.TodoListPageResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ToDoListControllerTest extends MockMvcTest {

    private String toDoUri = "/todo";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getTodoList() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(toDoUri + "/list/1/5")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        TodoListPageResult todoListPageResults = super.mapFromJson(content, TodoListPageResult.class);
        assertTrue(todoListPageResults.getDataList() != null);
        assertTrue(todoListPageResults.getPageInfo().getPageNumber() == 1);
    }


    @Test
    public void addTodo() throws Exception {

        ToDoAddPayload toDoAddPayload = new ToDoAddPayload();
        toDoAddPayload.setToDo("방청소");

        JSONArray refArray = new JSONArray();
        refArray.add(1);
        refArray.add(3);
        refArray.add(4);
        toDoAddPayload.setRefId(refArray);

        String inputJson = super.mapToJson(toDoAddPayload);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(toDoUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), "정상 등록");
    }


    @Test
    public void addTodo_BadRequest() throws Exception {

        JSONObject toDoObj = new JSONObject();
        toDoObj.put("todo", "산책하기");

        JSONArray refArray = new JSONArray();
        refArray.add(1);
        refArray.add(3);
        refArray.add(4);
        toDoObj.put("refId", refArray);

        String inputJson = super.mapToJson(toDoObj);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(toDoUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    public void addTodo_ToDo_Validation() throws Exception {

        ToDoAddPayload toDoAddPayload = new ToDoAddPayload();
        JSONArray refArray = new JSONArray();
        refArray.add(1);
        refArray.add(3);
        refArray.add(4);
        toDoAddPayload.setRefId(refArray);

        String inputJson = super.mapToJson(toDoAddPayload);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(toDoUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isBadRequest())
                .andExpect(
                        (rslt) -> assertTrue(rslt.getResolvedException().getClass()
                                .isAssignableFrom(MethodArgumentNotValidException.class))
                )
                .andReturn();
    }

    @Test
    public void addTodo_RefIds_Validation() throws Exception {

        ToDoAddPayload toDoAddPayload = new ToDoAddPayload();
        toDoAddPayload.setToDo("방청소");

        JSONArray refArray = new JSONArray();
        toDoAddPayload.setRefId(refArray);

        String inputJson = super.mapToJson(toDoAddPayload);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(toDoUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(status().isOk())
                .andReturn();
    }


    @Test
    public void updateTodo() throws Exception {
        String uri = toDoUri + "/0";

        ToDoUpdatePayload toDoUpdatePayload = new ToDoUpdatePayload();
        toDoUpdatePayload.setToDo("유자차담그기");

        String inputJson = super.mapToJson(toDoUpdatePayload);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "true");
    }


    @Test
    public void updateEndYn() throws Exception {

        ToDoUpdatePayload toDoUpdatePayload = new ToDoUpdatePayload();
        toDoUpdatePayload.setEndYn("Y");

        String inputJson = super.mapToJson(toDoUpdatePayload);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(toDoUri + "/0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "true");
    }

    @Test
    public void updateTodo_NotFound() throws Exception {

        ToDoUpdatePayload toDoUpdatePayload = new ToDoUpdatePayload();
        toDoUpdatePayload.setToDo("유자차담그기");

        String inputJson = super.mapToJson(toDoUpdatePayload);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(toDoUri + Integer.MAX_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isNotFound())
                .andReturn();

    }

}
