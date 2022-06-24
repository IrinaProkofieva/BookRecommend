package com.ssau.bookrecommend.exception;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(){
        super("Пользователь не найден!");
    }
}
