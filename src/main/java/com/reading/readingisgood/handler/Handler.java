package com.reading.readingisgood.handler;

import com.reading.readingisgood.constants.ErrorCodes;
import com.reading.readingisgood.exception.Error;
import com.reading.readingisgood.exception.OrderNotFoundException;
import com.reading.readingisgood.exception.OutOfStockException;
import com.reading.readingisgood.exception.UserIsExistException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class Handler {
    private static final Logger log = LoggerFactory.getLogger(Handler.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> handleException(Exception e) {
        log.error("An unknown exception occurred!", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(generateError(ErrorCodes.INTERNAL_ERROR, e.getMessage()));
    }

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<Error> handleException(OrderNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(generateError(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler({OutOfStockException.class})
    public ResponseEntity<Error> handleException(OutOfStockException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(generateError(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler({UserIsExistException.class})
    public ResponseEntity<Error> handleException(UserIsExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(generateError(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Error> handleException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        if (!allErrors.isEmpty()) {
            String defaultMessage = ((ObjectError)allErrors.get(0)).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateError(ErrorCodes.VALIDATION_ERROR, defaultMessage));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateError(ErrorCodes.INTERNAL_ERROR, "Validation error!"));
    }

    private Error generateError(int code, String message) {
        return Error.builder()
                .code(code)
                .message(message)
                .timestamp(new DateTime().getMillis())
                .build();
    }
}
