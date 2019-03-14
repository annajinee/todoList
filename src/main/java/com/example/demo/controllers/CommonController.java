package com.example.demo.controllers;

import com.example.demo.common.ErrorCode;
import com.example.demo.common.Response;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonController {

    protected Logger logger = Logger.getLogger(getClass().getName());

    protected ResponseEntity<?> invalidParameterResponse(String param) throws Exception {
        return Response.getResult("Invalid Parameter : " + param, ErrorCode.INVALID_PARAMETER, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
