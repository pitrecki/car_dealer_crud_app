package org.pitrecki.car_dealer_crud_app.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    private static final String PAYLOAD = "Payload: {}";
    private static final String RESPONSE = "Response: {}";
    private static final String METHOD = "Method: {}";

    @Before("@annotation(org.pitrecki.car_dealer_crud_app.aspect.Logging)")
    public void logPayload(JoinPoint joinPoint) {
        log.info(METHOD, joinPoint.getSignature().toShortString());
        for (Object arg : joinPoint.getArgs()) {
            log.info(PAYLOAD, arg);
        }
    }

    @AfterReturning(value = "@annotation(org.pitrecki.car_dealer_crud_app.aspect.Logging)", returning = "returnValue")
    public void logAfterReturning(Object returnValue) {
        log.info(RESPONSE, returnValue);
    }

    @AfterThrowing(value = "@annotation(org.pitrecki.car_dealer_crud_app.aspect.Logging)", throwing = "e")
    public void logAfterThrowing(Exception e) {
        log.error("", e);
    }
}
