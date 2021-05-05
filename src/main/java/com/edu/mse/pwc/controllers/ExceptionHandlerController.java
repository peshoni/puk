package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.utils.P;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public void defaultErrorHandler(HttpServletRequest request, Exception e) {
        if (!(e instanceof IOException)) { // reset by peer, broken pipe, etc..
            // debug log
            P.syso(e);
        } else {
            P.syso(e.getMessage());
        }
    }
}