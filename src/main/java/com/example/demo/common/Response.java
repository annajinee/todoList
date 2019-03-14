package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
    public static ResponseEntity<?> getResult(String msg, ErrorCode errorCode, HttpStatus httpStatus) throws Exception {
        ResultCode resultCode = new ResultCode();
        resultCode.setStatus(httpStatus);
        resultCode.setErrorCode(errorCode);
        resultCode.setMessage(msg);
        return new ResponseEntity<>(resultCode, httpStatus);
    }
}
