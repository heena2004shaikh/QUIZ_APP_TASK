package com.demo.Quiz_app.service.user;

import com.demo.Quiz_app.entities.User;

public interface UserService {

    User createUser(User user);

    Boolean hasUserWithEmail(String email);

    User login(User user);
    }
