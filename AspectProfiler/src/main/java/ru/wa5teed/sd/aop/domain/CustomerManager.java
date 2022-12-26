package ru.wa5teed.sd.aop.domain;


public interface CustomerManager {
    int addCustomer(Customer customer);
    Customer findCustomer(int id);
}
