package com.example.demo.controllers;


import com.example.demo.common.ErrorCode;
import com.example.demo.common.Response;
import com.example.demo.service.ToDoListService;
import com.example.demo.util.StringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todo")
public class ToDoListController extends CommonController {

    @Autowired
    private ToDoListService toDoListService;


    @RequestMapping(value = "/list/{position}/{size}", method = RequestMethod.GET)
    public ResponseEntity<?> getTodoList(@PathVariable int position, @PathVariable int size) throws Exception {

        try {
            return new ResponseEntity<>(toDoListService.getTodoDataList(position, size), HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.toString());
            return Response.getResult("서버오류", ErrorCode.UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTodo(@RequestBody String payload) throws Exception {

        JSONObject reqObj = new JSONObject();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(payload);
            reqObj = (JSONObject) obj;

            String toDo = StringUtil.getJsonValue(reqObj, "toDo", "");
            if(StringUtil.IsNullOrEmpty(toDo).equals("")){
                return invalidParameterResponse("toDo");
            }

            JSONArray refId = (JSONArray)reqObj.get("refId");

            if (toDoListService.insertTodoData(toDo, refId))
                return new ResponseEntity<>("정상 등록", HttpStatus.OK);
            else
                return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {
            logger.error(ex.toString());
            return Response.getResult("서버오류", ErrorCode.UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    public ResponseEntity<?> getTodo(@PathVariable int seq) throws Exception {

        try {
            return new ResponseEntity<>(toDoListService.getTodoData(seq), HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.toString());
            return Response.getResult("Not found", ErrorCode.NOTFOUND, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTodo(@PathVariable int seq, @RequestBody String payload) throws Exception {

        JSONObject reqObj = new JSONObject();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(payload);
            reqObj = (JSONObject) obj;

            String toDo = StringUtil.getJsonValue(reqObj, "toDo", "");
            if (toDo.equals("")) {
                return invalidParameterResponse("toDo");
            }
            String endYn = StringUtil.getJsonValue(reqObj, "endYn", "");

            return new ResponseEntity<>(toDoListService.updateTodoData(seq, toDo, endYn), HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.toString());
            return Response.getResult("서버오류", ErrorCode.UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
