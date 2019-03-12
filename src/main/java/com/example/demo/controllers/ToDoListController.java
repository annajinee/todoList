package com.example.demo.controllers;


import com.example.demo.service.ToDoListService;
import com.example.demo.util.StaticHelper;
import com.example.demo.util.StringUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/todo")
public class ToDoListController extends CommonController{

    @Autowired
    private ToDoListService toDoListService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTodoList(@RequestBody String payload) throws Exception{

        JSONObject reqObj = new JSONObject();
        JSONParser parser = new JSONParser();

        return null;
    }



}
