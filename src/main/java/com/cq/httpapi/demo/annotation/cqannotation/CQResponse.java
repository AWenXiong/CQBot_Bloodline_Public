package com.cq.httpapi.demo.annotation.cqannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CQResponse {
    String ResponseStartFlag() default "";

    String ResponseEndFlag() default "";

    String SenderLimit() default "";
}