package com.reading.readingisgood.exception;

import com.reading.readingisgood.constants.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderNotFoundException extends RuntimeException {
    private final int code;

    public int getCode() {
        return this.code;
    }

    public OrderNotFoundException() {
        super("Orde does not exist!");
        this.code = ErrorCodes.ORDER_NOT_FOUND;
    }
}
