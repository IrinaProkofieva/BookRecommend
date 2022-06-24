package com.ssau.bookrecommend.controller;

import com.ssau.bookrecommend.model.User;
import com.ssau.bookrecommend.response.UserCreatedResponse;
import com.ssau.bookrecommend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(description = "Регистрация.")
    @PostMapping("/v1/users/sign_up")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserCreatedResponse signUp(@RequestParam @Length(min=4, max = 12) String login,
                                      @RequestParam @Length(min=4, max = 12) String password,
                                      HttpServletRequest request,
                                      HttpSession session){
        User created = userService.signUp(login, password);
        session.invalidate();
        request.getSession().setAttribute("userId", created.getId());
        log.info(String.format("Зарегистрирован новый пользователь с логином %s", login));
        return new UserCreatedResponse(created.getId());
    }

    @Operation(description = "Авторизация.")
    @PostMapping("/v1/users/sign_in")
    @ResponseStatus(code = HttpStatus.OK)
    public UserCreatedResponse signIn(@RequestParam String login,
                       @RequestParam String password,
                       HttpServletRequest request,
                       HttpSession session){
        User user = userService.signIn(login, password);
        session.invalidate();
        request.getSession().setAttribute("userId", user.getId());
        log.info(String.format("Пользователь %s вошел в систему", login));
        return new UserCreatedResponse(user.getId());
    }

}
