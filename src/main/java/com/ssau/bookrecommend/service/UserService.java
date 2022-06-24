package com.ssau.bookrecommend.service;

import com.ssau.bookrecommend.model.User;

public interface UserService {
    User signIn(String login, String password);
    User signUp(String login, String password);
}
