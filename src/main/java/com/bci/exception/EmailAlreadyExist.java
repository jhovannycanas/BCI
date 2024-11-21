package com.bci.exception;

import lombok.Value;

@Value
public class EmailAlreadyExist extends Exception {

    public EmailAlreadyExist(String message) {
        super(message);
    }
}
