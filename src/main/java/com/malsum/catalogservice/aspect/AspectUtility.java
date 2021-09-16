package com.malsum.catalogservice.aspect;

import com.malsum.catalogservice.entity.Item;
import com.malsum.catalogservice.exceptions.NotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class AspectUtility {



    private Logger myLogger = Logger.getLogger(getClass().getName());


//    @Before(value = "execution(* com.malsum.catalogservice.dao.*.*(..))")
//    public void customizedDaoEsception(JoinPoint joinPoint){
//        Object[] objects = joinPoint.getArgs();
//        for (Object o : objects){
//            myLogger.warning(o.toString());
//        }
//    }
//    @Around(value = "execution(* com.malsum.catalogservice.service.ItemServiceImpl.get*(..))")
//    public void NotFoundException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        Object obj = null;
//        try{
//            obj = proceedingJoinPoint.proceed();
//            if (obj == null){
//                myLogger.info("In aspect");
//                throw new NotFoundException("Id not found");
//
//            }
//        }catch (Exception e){
//            throw e;
//        }
//
//    }



//    @AfterReturning(pointcut = "execution(* com.malsum.catalogservice.service.ItemServiceImpl.getItem(..))", returning = "result")
//    public Object notFound(JoinPoint joinPoint, Item result){
//        String method = joinPoint.getSignature().toString();
//        myLogger.info("\n=========== @After returning "+method);
//        if (result==null){
//            throw new NotFoundException("Item not found: in aspect");
//        }
//        return result;
//    }

//    @AfterThrowing(pointcut = "execution(* com.malsum.catalogservice.*.*.*(..))", throwing = "exception")
//    public void logExceptionLine(Exception exception){
//        myLogger.warning(exception.getCause());
//    }


    //advices i want:
    //throw custom exceptions based on returning values in item service
    //log stack trace
}
