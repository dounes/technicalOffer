package com.fr.testairfrance.usermanagement.aspect;

import com.fr.testairfrance.usermanagement.model.User;
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
public class RegisterUserAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution (* com.fr.testairfrance.usermanagement.service.IUserService.retrieveUser(..))")
    public void methodCall() {}

    @Before(value = "methodCall() and args(user)")
    public void beforeAdvice(JoinPoint joinPoint, User user) {
        LOGGER.info("Before method:" + joinPoint.getSignature());
        LOGGER.info("Attempt to register user with name - " + user.getName());
    }

    @After(value = "methodCall() and args(user)")
    public void afterAdvice(JoinPoint joinPoint, User user) {
        LOGGER.info("After method:" + joinPoint.getSignature());
        LOGGER.info("User " + user.getName() + " born on "+ user.getBirthDate() +" is successfully registered");
    }

    @Around("@annotation(com.fr.testairfrance.usermanagement.aspect.LogExecutionTime)")
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
        LOGGER.info(stopWatch.prettyPrint());
        return result;
    }
}
