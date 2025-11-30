package com.example.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.test.controller..*(..))")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println(">>>>>> Entering: " + joinPoint.getSignature());
        System.out.println(">>>>>> Arguments: " + Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();

        long duration = System.currentTimeMillis() - start;
        System.out.println(">>>>>> Exiting: " + joinPoint.getSignature());
        System.out.println(">>>>>> Result: " + result);
        System.out.println(">>>>>> Duration: " + duration + " ms");

        return result;
    }
}