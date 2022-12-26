package ru.wa5teed.sd.aop.profiler;

import java.lang.reflect.Method;

public class MethodProfile {
    private final Method method;
    private Integer invokeCount;
    private Long execTimeMillis;

    public MethodProfile(Method method, Integer invokeCount, Long execTimeMillis) {
        this.method = method;
        this.invokeCount = invokeCount;
        this.execTimeMillis = execTimeMillis;
    }

    public double averageExecTime() {
        return execTimeMillis / (double) invokeCount;
    }

    public Method getMethod() {
        return method;
    }

    public Integer getInvokeCount() {
        return invokeCount;
    }

    public Long getExecTimeMillis() {
        return execTimeMillis;
    }

    public void setInvokeCount(Integer invokeCount) {
        this.invokeCount = invokeCount;
    }

    public void setExecTimeMillis(Long execTimeMillis) {
        this.execTimeMillis = execTimeMillis;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s, All time: %d ms, Average time: %f ms, Exec count: %d]",
                method.getName(),
                execTimeMillis,
                averageExecTime(),
                invokeCount
        );
    }
}
