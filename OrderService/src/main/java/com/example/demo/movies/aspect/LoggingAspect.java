package com.example.demo.movies.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.demo.movies.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing method: " + joinPoint.getSignature().getName() + " in class: " + joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.movies.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method executed: " + joinPoint.getSignature().getName() + " in class: " + joinPoint.getTarget().getClass().getSimpleName() + ", Return value: " + result);
    }
}
