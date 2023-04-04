package com.microservices.orderservice.rest.response;

import com.microservices.orderservice.common.enums.ErrorCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorResponse implements Serializable {

    private Integer code;

    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getValue();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(ErrorCode errorCode, String errorMessage) {
        this.code = errorCode.getValue();
        this.message = errorMessage;
    }

}
