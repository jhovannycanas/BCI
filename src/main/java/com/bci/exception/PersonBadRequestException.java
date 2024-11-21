package com.bci.exception;

public class PersonBadRequestException extends RuntimeException {

  public PersonBadRequestException(String message) {

    super(message);
  }

}
