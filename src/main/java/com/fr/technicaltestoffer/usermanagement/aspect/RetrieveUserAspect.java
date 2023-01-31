package com.fr.technicaltestoffer.usermanagement.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class RetrieveUserAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution (* com.fr.technicaltestoffer.usermanagement.service.IUserService.getUser(..))")
    public void methodCall() {}

    @Before(value = "methodCall() and args(name,birthDate)")
    public void beforeAdvice(JoinPoint joinPoint, String name, String birthDate) {
        LOGGER.info("Before method:" + joinPoint.getSignature());
        LOGGER.info("Retrieving User with name - " + name + " and birthdate - " + birthDate);
    }

    @After(value = "methodCall() and args(name,birthDate)")
    public void afterAdvice(JoinPoint joinPoint, String name, String birthDate) {
        LOGGER.info("After method:" + joinPoint.getSignature());
    }

    @Around("@annotation(com.fr.technicaltestoffer.usermanagement.aspect.LogExecutionTime)")
    public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        // Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        // Measure method execution time
        StopWatch stopWatch = new StopWatch(className + "->" + methodName);
        stopWatch.start(methodName);
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        // Log method execution time
        LOGGER.info("Processing time : "+ stopWatch.getTotalTimeSeconds());
        return result;
    }
}
