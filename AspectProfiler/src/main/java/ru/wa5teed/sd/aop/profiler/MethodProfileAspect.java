package ru.wa5teed.sd.aop.profiler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MethodProfileAspect {

    @Around("execution(* ru.wa5teed.sd.aop.domain..*(..))")
    public Object profileMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodProfiler profiler = MethodProfiler.methodProfiler;
        long startNs = System.nanoTime();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            profiler.update(joinPoint.getSignature(), System.nanoTime() - startNs);
        }
    }
}
