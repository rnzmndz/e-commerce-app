package net.renzo.userservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* net.renzo.userservice.*.*.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        LOGGER.info("Method execution started: {}", joinPoint.getSignature());
    }
}
