package com.bci.control;

import com.bci.common.Constants;
import com.bci.controller.response.BasicResponse;
import com.bci.exception.EmailAlreadyExistsException;
import com.bci.exception.PersonBadRequestException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({PersonBadRequestException.class})
  public ResponseEntity<Object> handleStudentNotFoundException(
      PersonBadRequestException exception) {

    BasicResponse<String> response = new BasicResponse<>(exception.getMessage());
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler({EmailAlreadyExistsException.class})
  public ResponseEntity<Object> handleStudentAlreadyExistsException(
      EmailAlreadyExistsException exception) {

    BasicResponse<String> response = new BasicResponse<>(exception.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<BasicResponse<String>> handleRuntimeException(RuntimeException exception) {

    BasicResponse<String> response = new BasicResponse<>(Constants.ERROR_RESPONSE);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

}
