package com.codegym.furamaresortmanagement.service.customer;

import com.codegym.furamaresortmanagement.model.customer.CustomerType;

import java.util.List;

public interface ICustomerTypeService {
    List<CustomerType> findAll();
}
