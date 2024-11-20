package com.bci.controller;

import com.bci.controller.request.UserRequest;
import com.bci.controller.response.UserResponse;
import com.bci.model.User;
import com.bci.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/bci/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid UserRequest userRequest) {

        User user = userService.createUser(userRequest);
        UserResponse.builder()
                .id(user.getId())
                .token(user.getToken())
                .created(user.getCreated())
                .lastLogin(user.getLastLogin())
                .isActive(user.isActive())
                .build();

        return new ResponseEntity(null, HttpStatus.CREATED);
    }

}
