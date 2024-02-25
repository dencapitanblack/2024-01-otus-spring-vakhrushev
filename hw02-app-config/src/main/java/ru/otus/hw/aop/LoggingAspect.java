package ru.otus.hw.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* ru.otus.hw.config.*.*(..))")
    public void loggingBefore(JoinPoint joinPoint) {
        System.out.println("Working " + joinPoint.getSignature().getName());
    }
}
