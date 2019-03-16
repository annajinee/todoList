package com.kakaopay.test.controllers;

import com.kakaopay.test.AbstractTest;
import com.kakaopay.test.model.dto.ToDoAddPayload;
import com.kakaopay.test.model.dto.ToDoUpdatePayload;
import com.kakaopay.test.model.dto.TodoListPageResult;
import com.kakaopay.test.model.entity.ToDoListData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ToDoListControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private String toDoUri="/todo";

    @Test
    public void getTodoList() throws Exception {
        String uri = toDoUri + "/list/0/5";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        System.out.println(mvcResult);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        TodoListPageResult[] todoListPageResults = super.mapFromJson(content, TodoListPageResult[].class);
        assertTrue(todoListPageResults.length > 0);
    }
    @Test
    public void addTodo() throws Exception {
        String uri = toDoUri;
        ToDoAddPayload toDoUpdatePayload = new ToDoAddPayload();
        toDoUpdatePayload.setToDo("방청소");
        JSONArray refArray = new JSONArray();
        refArray.add(1);
        refArray.add(3);
        refArray.add(4);
        toDoUpdatePayload.setRefId(refArray);

        String inputJson = super.mapToJson(toDoUpdatePayload);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "정상 등록");
    }

}
