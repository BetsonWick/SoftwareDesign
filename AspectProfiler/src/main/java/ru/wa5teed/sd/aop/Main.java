package ru.wa5teed.sd.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.wa5teed.sd.aop.domain.Customer;
import ru.wa5teed.sd.aop.domain.CustomerManager;
import ru.wa5teed.sd.aop.drawing.JavaFxApp;
import ru.wa5teed.sd.aop.profiler.MethodProfiler;


public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        if (args.length > 0) {
            MethodProfiler.PROFILER_PATH_ROOT = args[0];
        }
        CustomerManager customerManager = ctx.getBean(CustomerManager.class);
        int id = customerManager.addCustomer(new Customer("Petr"));
        Customer customer = customerManager.findCustomer(id);

        System.out.println("Found customer name: " + customer.name);
        JavaFxApp.statistics = MethodProfiler.methodProfiler.getStatistics();
        new JavaFxApp().run();
    }
}
