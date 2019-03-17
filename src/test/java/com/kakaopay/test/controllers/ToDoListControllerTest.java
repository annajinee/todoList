package com.kakaopay.test.controllers;

import com.kakaopay.test.MockMvcTest;
import com.kakaopay.test.model.dto.ToDoAddPayload;
import com.kakaopay.test.model.dto.ToDoUpdatePayload;
import com.kakaopay.test.model.dto.TodoListPageResult;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
public class ToDoListControllerTest extends MockMvcTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private String toDoUri = "/todo";


    @Test
    public void getTodoList() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(toDoUri + "/list/0/5")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        TodoListPageResult todoListPageResults = super.mapFromJson(content, TodoListPageResult.class);

        System.out.println("response : " + content);
        assertTrue(todoListPageResults.getDataList() != null);
        assertTrue(todoListPageResults.getPageInfo().getPageNumber() == 0);

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

        System.out.println("request : " + inputJson);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(toDoUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        System.out.println("response : " + mvcResult.getResponse().getContentAsString());

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), "정상 등록");
    }


    @Test
    public void updateTodo() throws Exception {
        String uri = toDoUri + "/0";

        ToDoUpdatePayload toDoUpdatePayload = new ToDoUpdatePayload();
        toDoUpdatePayload.setToDo("유자차담그기");

        String inputJson = super.mapToJson(toDoUpdatePayload);

        System.out.println("request : " + inputJson);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        System.out.println("response : " + mvcResult.getResponse().getContentAsString());

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "true");
    }


    @Test
    public void updateEndYn() throws Exception {
        String uri = toDoUri + "/0";

        ToDoUpdatePayload toDoUpdatePayload = new ToDoUpdatePayload();
        toDoUpdatePayload.setEndYn("Y");

        String inputJson = super.mapToJson(toDoUpdatePayload);

        System.out.println("request : " + inputJson);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        System.out.println("response : " + mvcResult.getResponse().getContentAsString());

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "true");
    }

}
