package com.fh.commons;


import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)  /*该注解用在方法上*/
@Retention(RetentionPolicy.RUNTIME)  /*注解在运行时生效*/
public @interface Ignore {



}
