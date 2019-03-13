package com.example.demo.controllers;


import com.example.demo.service.ToDoListService;
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


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<?> getTodoList(@RequestBody String payload) throws Exception {

        JSONObject reqObj = new JSONObject();
        JSONParser parser = new JSONParser();
        logger.trace(payload);

        try {
            Object obj = parser.parse(payload);
            reqObj = (JSONObject) obj;
            String postionStr = StringUtil.getJsonValue(reqObj,"pos", "");
            String sizeStr = StringUtil.getJsonValue(reqObj,"size", "");
            int position = StringUtil.parseInt(postionStr);
            if (position < 0) {
                return new ResponseEntity<>("파라미터 오류 : " + "position", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            int size = StringUtil.parseInt(sizeStr);
            if (size < 0) {
                return new ResponseEntity<>("파라미터 오류 : " + "size", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(toDoListService.getTodoDataList(position, size), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return new ResponseEntity<>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTodo(@RequestBody String payload) throws Exception {

        JSONObject reqObj = new JSONObject();
        JSONParser parser = new JSONParser();
        logger.trace(payload);

        try {
            Object obj = parser.parse(payload);
            reqObj = (JSONObject) obj;

            String toDo = StringUtil.getJsonValue(reqObj, "toDo", "");
            if(StringUtil.IsNullOrEmpty(toDo).equals("")){
                return new ResponseEntity<>("파라미터 오류 : " + "toDo", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String refId = StringUtil.getJsonValue(reqObj, "refId", "");
            if(StringUtil.IsNullOrEmpty(refId).equals("")){
                return new ResponseEntity<>("파라미터 오류 : " + "refId", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (toDoListService.insertTodoData(toDo, refId))
                return new ResponseEntity<>("정상 등록", HttpStatus.OK);
            else
                return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {
            logger.error(ex.toString());
            return new ResponseEntity<>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    public ResponseEntity<?> getTodo(@PathVariable String seq) throws Exception {

        try {
            if (StringUtil.IsNullOrEmpty(seq).equals("")) {
                return new ResponseEntity<>("파라미터 오류 : " + "seq", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(toDoListService.getTodoData(StringUtil.parseInt(seq)), HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.toString());
            return new ResponseEntity<>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTodo(@PathVariable String seq, @RequestBody String payload) throws Exception {

        JSONObject reqObj = new JSONObject();
        JSONParser parser = new JSONParser();
        logger.trace(payload);

        try {
            if (StringUtil.IsNullOrEmpty(seq).equals("")) {
                return new ResponseEntity<>("파라미터 오류 : " + "seq", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String toDo = StringUtil.getJsonValue(reqObj, "toDo", "");
            if (toDo.equals("")) {
                return new ResponseEntity<>("파라미터 오류 : " + "toDo", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String endYn = StringUtil.getJsonValue(reqObj, "endYn", "");

            return new ResponseEntity<>(toDoListService.updateTodoData(StringUtil.parseInt(seq), toDo, endYn), HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.toString());
            return new ResponseEntity<>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
