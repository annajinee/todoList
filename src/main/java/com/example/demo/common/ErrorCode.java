package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonValue;


public enum ErrorCode {
    NOTFOUND(1001),
    INVALID_PARAMETER(1002),
    UNKNOWN_ERROR(1003);

    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
