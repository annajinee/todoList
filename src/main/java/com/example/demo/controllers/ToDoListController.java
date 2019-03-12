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
public class ToDoListController extends CommonController {

    @Autowired
    private ToDoListService toDoListService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTodo(@RequestBody String payload) throws Exception {

        JSONObject reqObj = new JSONObject();
        JSONParser parser = new JSONParser();
        logger.trace(payload);

        try {
            Object obj = parser.parse(payload);
            reqObj = (JSONObject) obj;

            String work = StaticHelper.getJsonValue(reqObj, "work", "");

            if (work.equals("")) {
                return new ResponseEntity<>("파라미터 오류 : " + "work", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if (toDoListService.insertTodoData(work))
                return new ResponseEntity<>("정상 등록", HttpStatus.OK);
            else
                return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {
            logger.error("addTodo", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    public ResponseEntity<?> getTodo(@PathVariable String seq) throws Exception {

        try {
            if (StringUtil.strToNullAndEnptyStr(seq).equals("")) {
                return new ResponseEntity<>("파라미터 오류 : " + "seq", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(toDoListService.getTodoData(StringUtil.parseInt(seq)), HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("getTodo", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
