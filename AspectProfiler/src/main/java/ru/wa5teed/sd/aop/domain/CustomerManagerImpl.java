package ru.wa5teed.sd.aop.domain;

import ru.wa5teed.sd.aop.dao.CustomerInMemoryDao;


public class CustomerManagerImpl implements CustomerManager {
    CustomerInMemoryDao customerDao = new CustomerInMemoryDao();

    public CustomerManagerImpl(CustomerInMemoryDao customerDao) {
        this.customerDao = customerDao;
    }

    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    public Customer findCustomer(int id) {
        return customerDao.findCustomer(id);
    }
}
