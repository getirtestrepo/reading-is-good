package com.reading.readingisgood.exception;

import com.reading.readingisgood.constants.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserIsExistException extends RuntimeException {
    private final int code;

    public int getCode() {
        return this.code;
    }

    public UserIsExistException() {
        super("User already registered with given phone number");
        this.code = ErrorCodes.USER_EXIST_ERROR;
    }
}
