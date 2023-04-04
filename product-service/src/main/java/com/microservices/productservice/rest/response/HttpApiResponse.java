package com.microservices.productservice.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class HttpApiResponse<T> implements Serializable {

    private boolean success;

    private T data;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private ErrorResponse error;

    public HttpApiResponse(ErrorResponse error) {
        this.success = false;
        this.data = null;
        this.error = error;
    }

    public HttpApiResponse(T data) {
        this.success = true;
        this.data = data;
        this.error = null;
    }

}
