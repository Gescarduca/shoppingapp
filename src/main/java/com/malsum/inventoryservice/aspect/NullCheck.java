package com.malsum.inventoryservice.aspect;


import com.malsum.inventoryservice.exceptions.ItemNotFound;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class NullCheck {

    @Around(value = "execution(* com.malsum.inventoryservice.service.*.*(..))")
    public Object nullValuesBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Object obj;
        Arrays.stream(args).forEach(o -> {
            if (o==null){
                throw new RuntimeException("Value entered is null");
            }
        });
        try{
            obj = proceedingJoinPoint.proceed();
            if (obj == null){
                throw new ItemNotFound("Item was not found in db");
            }
        }catch (Exception e){
            throw e;
        }
        return obj;
    }

//    @Around(value = "execution(* com.malsum.inventoryservice.service.*.*(..))")
//    public void log(ProceedingJoinPoint joinPoint){
//
//    }
}
