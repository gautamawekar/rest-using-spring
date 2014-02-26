package com.gawekar.spring.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gawekar.spring.rest.model.ErrorInfo;
import com.gawekar.spring.rest.model.MyData;
import com.google.common.collect.ImmutableMap;

//NOTE: here we have used RestController hence we need not use @ResponseBody annotation.
@RestController
public class ErrorHandlingController {

    private final Map<Integer, MyData> DATA = ImmutableMap.of(1, new MyData("Gautam1", "Awekar1"), 2, new MyData("Gautam2", "Awekar2"));

    /**
     * Note: if method argument annotated with @PathVariable can’t be casted to
     * specified type (in our case to int), it will be exposed as String. Hence
     * it can cause a TypeMismatchException.
     * 
     * @param id
     * @return
     */

    @RequestMapping(value = "/error/{id}", method = RequestMethod.GET,produces={"application/xml", "application/json","text/mydata"})
    @ResponseStatus(HttpStatus.OK)
    public MyData getData(@PathVariable int id) {
        MyData data = DATA.get(id);
        if (data == null) {
            throw new UserNotFoundException(id);
        }
        return data;
    }
    
    @ExceptionHandler(TypeMismatchException.class)  
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)  
    public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {  
        String errorURL = req.getRequestURL().toString();  
        return new ErrorInfo(errorURL, "Illegal argument in the request");  
    }
    
    public static class UserNotFoundException extends RuntimeException{
        private static final long serialVersionUID = 1L;
        private final int id;
        public UserNotFoundException(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
    }

}
