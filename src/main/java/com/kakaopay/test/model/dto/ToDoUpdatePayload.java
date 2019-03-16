package com.kakaopay.test.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ToDoUpdatePayload {
    private String toDo;
    private String endYn;
}
