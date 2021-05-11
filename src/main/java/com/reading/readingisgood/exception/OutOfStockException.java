package com.reading.readingisgood.exception;

import com.reading.readingisgood.constants.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OutOfStockException extends RuntimeException {
    private final int code;

    public int getCode() {
        return this.code;
    }

    public OutOfStockException() {
        super("Stock is empty");
        this.code = ErrorCodes.STOCK_IS_EMPTY;
    }
}
