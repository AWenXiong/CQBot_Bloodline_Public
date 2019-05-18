package com.cq.httpapi.demo.exception;

import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.RestfulEntityKit;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.cq.httpapi.demo.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SZJException.class)
    @ResponseBody
    public RestfulEntityKit handle(SZJException e) {
        return RestfulEntityKit.getFailure(SZJErrorCode.valueOf(e.getErrorCode()));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestfulEntityKit handle(Exception e) {
        return RestfulEntityKit.getFailure("hello");
    }
}
