package com.kakaopay.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
    public static ResponseEntity<?> getResult(String msg, HttpStatus httpStatus) throws Exception {
        ResultCode resultCode = new ResultCode();
        resultCode.setStatus(httpStatus);
        resultCode.setMessage(msg);
        return new ResponseEntity<>(resultCode, httpStatus);
    }
}
