package com.kakaopay.test.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResultCode {
    private HttpStatus status;
    private String message;
}
