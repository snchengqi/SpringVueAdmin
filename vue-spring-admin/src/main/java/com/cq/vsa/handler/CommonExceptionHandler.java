package com.cq.vsa.handler;

import com.cq.vsa.exception.CommonException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: chengqi
 * @Date: Created in 15:16 2019/4/11
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<String> handleCommonException(CommonException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
