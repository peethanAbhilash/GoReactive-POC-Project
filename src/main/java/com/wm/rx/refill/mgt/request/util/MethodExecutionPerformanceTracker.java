package com.wm.rx.refill.mgt.request.util;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class MethodExecutionPerformanceTracker {
  public Object trackPerformaceInTime(ProceedingJoinPoint joinPoint) throws Throwable {

    long startTime = System.currentTimeMillis();

    Object returnVal = joinPoint.proceed();

    log.info("##============> RX-Refill:: execution time taken : {} is {} milliseconds", joinPoint,
        (System.currentTimeMillis() - startTime));
    return returnVal;
  }


}
