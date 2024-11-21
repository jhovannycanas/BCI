package com.bci.control;

import com.bci.exception.EmailAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EmailAlreadyExist e) {
        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder()
                .mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
