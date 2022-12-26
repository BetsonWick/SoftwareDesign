package ru.wa5teed.sd.aop.profiler;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodProfiler {
    public static String PROFILER_PATH_ROOT = "";
    public static MethodProfiler methodProfiler = new MethodProfiler();
    private final Map<Method, MethodProfile> statistics = new HashMap<>();

    public void update(Signature signature, Long execTimeMillis) {
        if (signature instanceof MethodSignature methodSignature) {
            Method method = methodSignature.getMethod();
            if (method.getDeclaringClass().getPackageName().startsWith(PROFILER_PATH_ROOT)) {
                statistics.merge(
                        method,
                        new MethodProfile(method, 1, execTimeMillis),
                        (old, new_) -> {
                            new_.setInvokeCount(old.getInvokeCount() + 1);
                            new_.setExecTimeMillis(old.getExecTimeMillis() + execTimeMillis);
                            return new_;
                        }
                );
            }
        }
    }

    public Map<Method, MethodProfile> getStatistics() {
        return statistics;
    }

}
