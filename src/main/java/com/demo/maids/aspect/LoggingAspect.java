package com.demo.maids.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("com.demo.maids.aspect.PointCuts.comboPointCutDeceleration()")
    public void logRequests(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.err.println("Trying to access  "+signature);
    }
    @AfterThrowing(pointcut="com.demo.maids.aspect.PointCuts.comboPointCutDeceleration()", throwing = "exception")
    public void logExceptions(JoinPoint joinPoint, Throwable exception){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String message = exception.getMessage();
        System.err.println("Throw "+message+" at "+signature);
    }
    @AfterReturning(pointcut="com.demo.maids.aspect.PointCuts.comboPointCutDeceleration()", returning = "result")
    public void logResponses(JoinPoint joinPoint, Object result){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.err.println("Returning  "+result+" from "+signature);
    }
}
