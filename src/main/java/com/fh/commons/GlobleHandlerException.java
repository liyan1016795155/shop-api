package com.fh.commons;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleHandlerException {

    @ExceptionHandler(LoginException.class)
    public ServerResponse handlerLoginException(Exception e){
         //e.printStackTrace();
        return ServerResponse.loginError();
    }


    @ExceptionHandler(MyException.class)
    public ServerResponse handlerMyException(Exception e){
             e.printStackTrace();
        return ServerResponse.error();
    }

    @ExceptionHandler(Exception.class)
    public ServerResponse handlerException(Exception e){
        e.printStackTrace();
        return ServerResponse.error();
    }
}
