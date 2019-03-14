package com.example.demo.controllers;

import com.example.demo.AbstractTest;
import com.example.demo.model.dto.TodoListPageResult;
import com.example.demo.model.dto.TodoListResult;
import com.example.demo.model.entity.ToDoListData;
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

    private String toDoUri="/api/todo";

    @Test
    public void getTodoList() throws Exception {
        String uri = toDoUri + "/list";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        TodoListPageResult[] todoListPageResults = super.mapFromJson(content, TodoListPageResult[].class);
        assertTrue(todoListPageResults.length > 0);
    }
    @Test
    public void addTodo() throws Exception {
        String uri = toDoUri;
        ToDoListData toDoListData = new ToDoListData();
        toDoListData.setToDo("방청소");
        String inputJson = super.mapToJson(toDoListData);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "등록 성공");
    }

}
