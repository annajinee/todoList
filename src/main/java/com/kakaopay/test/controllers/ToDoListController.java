package com.kakaopay.test.controllers;


import com.kakaopay.test.service.ToDoListService;
import com.kakaopay.test.common.Response;
import com.kakaopay.test.model.dto.ToDoAddPayload;
import com.kakaopay.test.model.dto.ToDoUpdatePayload;;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/todo")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }


    @GetMapping(value = "/list/{position}/{size}")
    public ResponseEntity<?> getTodoList(@PathVariable int position, @PathVariable int size) throws Exception {
        try {
            return ResponseEntity.ok(toDoListService.getTodoDataList(position, size));
        } catch (Exception ex) {
            log.error(ex.toString());
            return Response.getResult("서버오류", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTodo(@RequestBody @Valid ToDoAddPayload toDoAddPayload) throws Exception {

        try {
            if (toDoListService.insertTodoData(toDoAddPayload.getToDo(), toDoAddPayload.getRefId()))
                return new ResponseEntity<>("정상 등록", HttpStatus.OK);
            else
                return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            log.error(ex.toString());
            return Response.getResult("서버오류", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/{seq}")
    public ResponseEntity<?> updateTodo(@PathVariable int seq, @RequestBody ToDoUpdatePayload toDoUpdatePayload) throws Exception {
        System.out.println(toDoUpdatePayload.toString());
        try {
            return ResponseEntity.ok(toDoListService.updateTodoData(seq, toDoUpdatePayload.getToDo(), toDoUpdatePayload.getEndYn()));

        } catch (UnsupportedOperationException ex) {
            return Response.getResult("참조된 일이 완료되지 않음", HttpStatus.NOT_MODIFIED);

        } catch (Exception ex) {
            log.error(ex.toString());
            return Response.getResult("서버오류", HttpStatus.BAD_REQUEST);

        }
    }
}
