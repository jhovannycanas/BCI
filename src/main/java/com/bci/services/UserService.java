package com.bci.services;

import com.bci.controller.request.UserRequest;
import com.bci.model.User;

public interface UserService {

    User createUser(UserRequest user);
}
