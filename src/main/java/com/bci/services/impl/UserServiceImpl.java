package com.bci.services.impl;

import com.bci.common.JsonWebToken;
import com.bci.controller.request.UserRequest;
import com.bci.model.User;
import com.bci.repositories.UserRepository;
import com.bci.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final JsonWebToken jsonWebToken;


    @Override
    public User createUser(UserRequest user) {
        //validar el correo que no exista en cache
        //validar el password tiene un formato adecuado y lanzar excepcion
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        newUser.setCreated(LocalDate.now());
        newUser.setUpdated(LocalDate.now());
        newUser.setToken(jsonWebToken.generateToken(user.getName()));
        newUser.setActive(true);
        return userRepository.save(newUser);
    }
}
