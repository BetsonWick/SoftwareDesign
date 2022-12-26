package ru.wa5teed.sd.aop.profiler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingExecutionTimeAspect {

    @Around("execution(* ru.wa5teed.sd.aop.domain..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startMs = System.currentTimeMillis();
        System.out.println("Start method " + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed(joinPoint.getArgs());

        System.out.println("Finish method " + joinPoint.getSignature().getName()
                + ", execution time in ns: " + (System.currentTimeMillis() - startMs));

        return result;
    }
}
