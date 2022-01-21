package service.Impl;

import Repository.CustomerRepository;
import Repository.Impl.CustomerRepositoryImpl;
import bean.Customer;
import bean.CustomerType;
import service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public void remove(String id) {
        customerRepository.remove(id);
    }

    @Override
    public List<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public List<CustomerType> getCustomerType() {
        return customerRepository.getCustomerType();
    }
}
