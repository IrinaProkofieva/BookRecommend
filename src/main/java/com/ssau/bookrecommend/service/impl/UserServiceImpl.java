package com.ssau.bookrecommend.service.impl;

import com.ssau.bookrecommend.exception.NoSuchUserException;
import com.ssau.bookrecommend.exception.UserAlreadyExistsException;
import com.ssau.bookrecommend.model.User;
import com.ssau.bookrecommend.repository.UserRepos;
import com.ssau.bookrecommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User signIn(String login, String password) {
        User user = userRepos.findByLogin(login).orElseThrow(NoSuchUserException::new);
        if (passwordEncoder.matches(password, user.getPwd())){
            return user;
        }
        else
            throw new NoSuchUserException();
    }

    @Override
    public User signUp(String login, String password) {
        userRepos.findByLogin(login).ifPresent(x-> {throw new UserAlreadyExistsException();});
        User user = new User(login, passwordEncoder.encode(password));
        userRepos.saveAndFlush(user);
        return user;
    }
}
