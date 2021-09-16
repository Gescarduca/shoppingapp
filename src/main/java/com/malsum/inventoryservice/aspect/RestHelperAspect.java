package com.malsum.inventoryservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

//@Aspect
//@Component
//@Order(2)
//public class RestHelperAspect {
//
////
////    private Logger myLogger = Logger.getLogger(getClass().getName());
////
////    @AfterThrowing(value = "execution(* com.malsum.inventoryservice.dao.*.get*(..))", throwing = "exc")
////    public void customizedDaoException(JoinPoint joinPoint, Throwable exc){
////        myLogger.warning(exc.getMessage());
////    }
//
//
//
//}
