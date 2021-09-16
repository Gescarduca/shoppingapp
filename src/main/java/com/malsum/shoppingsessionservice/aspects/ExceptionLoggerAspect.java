package com.malsum.shoppingsessionservice.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Logger;

@Aspect
public class ExceptionLoggerAspect {

    Logger logger = Logger.getLogger(Logger.class.getName());
//
//    @AfterThrowing(pointcut = "execution(* com.malsum.shoppingsessionservice.*.*.*(..))",throwing = "exception")
//    public void loggerExceptions(Exception exception){
//        logger.info(exception.getLocalizedMessage());
//        //logger.info(exception.getStackTrace().toString());
//    }

}
