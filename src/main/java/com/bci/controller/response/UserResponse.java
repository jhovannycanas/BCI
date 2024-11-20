package com.bci.controller.response;

import jakarta.validation.constraints.Email;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public class UserResponse {

    private UUID id;
    private LocalDate created;
    private LocalDate modified;
    private LocalDate lastLogin;
    private String token;
    private boolean isActive;
}
