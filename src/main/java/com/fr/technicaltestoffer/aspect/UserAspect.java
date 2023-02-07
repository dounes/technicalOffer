package com.fr.technicaltestoffer.aspect;

import com.fr.technicaltestoffer.dto.UserDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class UserAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within (com.fr.technicaltestoffer.service..*)")
    public void methodCall() {}

    @Before(value = "methodCall() and args(userDTO)")
    public void beforeAdvice(JoinPoint joinPoint, UserDTO userDTO) {
        LOGGER.info("Before method:" + joinPoint.getSignature());
        LOGGER.info("Retrieving User with name - " + userDTO.getFullname() + " and birthdate - " + userDTO.getBirthdate());
    }

    @After(value = "methodCall() and args(userDTO)")
    public void afterAdvice(JoinPoint joinPoint, UserDTO userDTO) {
        LOGGER.info("After method:" + joinPoint.getSignature());
    }

    @Around("@annotation(com.fr.technicaltestoffer.aspect.LogExecutionTime)")
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
