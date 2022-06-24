package com.ssau.bookrecommend.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){
        super("Пользователь с таким логином уже зарегистрирован!");
    }
}
