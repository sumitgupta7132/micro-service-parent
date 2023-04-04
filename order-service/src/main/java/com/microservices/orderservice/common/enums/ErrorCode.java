package com.microservices.orderservice.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    INVALID_REQUEST(1001, "Invalid fields"), NOT_FOUND(1002, "Requested data not found");

    private final int value;

    private final String message;

    @Override
    public String toString() {
        return this.name() + "(" + this.getValue() + ")";
    }
}
