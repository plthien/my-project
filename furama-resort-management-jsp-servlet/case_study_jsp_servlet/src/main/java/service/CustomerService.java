package service;

import bean.Customer;
import bean.CustomerType;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    void update(Customer customer);

    void remove(String id);

    List<Customer> findById(String id);

    List<Customer> findByName(String name);

    List<CustomerType> getCustomerType();
}
