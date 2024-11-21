package com.bci.controller.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public class PersonResponse {

    private UUID id;
    private LocalDate created;
    private LocalDate modified;
    private LocalDate lastLogin;
    private String token;
    private boolean isActive;
}
