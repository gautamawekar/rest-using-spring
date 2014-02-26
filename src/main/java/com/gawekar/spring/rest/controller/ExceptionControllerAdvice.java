package com.gawekar.spring.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gawekar.spring.rest.controller.ErrorHandlingController.UserNotFoundException;
import com.gawekar.spring.rest.model.ErrorInfo;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)  
    @ResponseStatus(value=HttpStatus.NOT_FOUND)  
    @ResponseBody
    public ErrorInfo handleUserNotFoundException(HttpServletRequest req, UserNotFoundException ex) {  
        String errorURL = req.getRequestURL().toString();  
        return new ErrorInfo(errorURL, "Unable to find user with id: " + ex.getId());  
    }
}
