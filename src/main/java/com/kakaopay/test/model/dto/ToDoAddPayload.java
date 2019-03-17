package com.kakaopay.test.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.simple.JSONArray;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ToDoAddPayload {

    @NotNull(message = "Invalid Parameter - toDo")
    private String toDo;
    private JSONArray refId;

}
