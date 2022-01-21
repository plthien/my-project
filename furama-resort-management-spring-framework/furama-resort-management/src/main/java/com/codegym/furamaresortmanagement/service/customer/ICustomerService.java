package com.codegym.furamaresortmanagement.service.customer;

import com.codegym.furamaresortmanagement.model.customer.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {
    Page<Customer> findCustomerByCustomerTypeId(int page, int size, String sortField, String sortDirection, Integer customerTypeId);

    Page<Customer> findAllCustomer(int page, int size, String sortField, String sortDirection);

    Page<Customer> findCustomerByKeyword(int page, int size, String sortField, String sortDirection, String keyword);

    Customer findOneById(String id);

    void saveCustomer(Customer customer);

    void deleteCustomer(String id);

    List<Customer> findAll();
}
