package com.cq.vsa.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: chengqi
 * @Date: Created in 14:47 2019/4/11
 */
@RestControllerAdvice
public class ValidatorExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        String message = fieldError.getField() + ":" + fieldError.getDefaultMessage();
        return message;
    }
}
