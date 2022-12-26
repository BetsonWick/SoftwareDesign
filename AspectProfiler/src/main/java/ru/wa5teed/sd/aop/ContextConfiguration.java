package ru.wa5teed.sd.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.wa5teed.sd.aop.dao.CustomerInMemoryDao;
import ru.wa5teed.sd.aop.domain.CustomerManager;
import ru.wa5teed.sd.aop.domain.CustomerManagerImpl;
import ru.wa5teed.sd.aop.profiler.LoggingExecutionTimeAspect;
import ru.wa5teed.sd.aop.profiler.MethodProfileAspect;


@Configuration
@EnableAspectJAutoProxy
public class ContextConfiguration {
    @Bean
    public CustomerManager customerManager() {
        return new CustomerManagerImpl(new CustomerInMemoryDao());
    }

    @Bean
    public LoggingExecutionTimeAspect aspect() {
        return new LoggingExecutionTimeAspect();
    }

    @Bean
    public MethodProfileAspect methodProfileAspect() {
        return new MethodProfileAspect();
    }
}
