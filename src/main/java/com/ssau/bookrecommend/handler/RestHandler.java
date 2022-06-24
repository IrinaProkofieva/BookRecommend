package com.ssau.bookrecommend.handler;

import com.ssau.bookrecommend.exception.NoSuchUserException;
import com.ssau.bookrecommend.exception.UserAlreadyExistsException;
import com.ssau.bookrecommend.response.DefaultErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
@CrossOrigin(origins = "http://localhost:3000/")
@ControllerAdvice
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class RestHandler {

    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public DefaultErrorResponse defaultExceptionHandler(Exception ex){
        log.error(ex.getMessage(), ex);
        return new DefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public DefaultErrorResponse signInExceptionHandler(NoSuchUserException ex){
        log.error(ex.getMessage(), ex);
        return new DefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public DefaultErrorResponse signUpExceptionHandler(UserAlreadyExistsException ex){
        log.error(ex.getMessage(), ex);
        return new DefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public DefaultErrorResponse authorisationExceptionHandler(AuthException ex){
        log.error(ex.getMessage(), ex);
        return new DefaultErrorResponse(ex.getMessage());
    }

}
