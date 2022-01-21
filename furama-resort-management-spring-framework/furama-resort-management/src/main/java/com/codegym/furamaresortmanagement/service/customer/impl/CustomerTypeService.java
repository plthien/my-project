package com.codegym.furamaresortmanagement.service.customer.impl;

import com.codegym.furamaresortmanagement.model.customer.CustomerType;
import com.codegym.furamaresortmanagement.repository.customer.ICustomerTypeRepository;
import com.codegym.furamaresortmanagement.service.customer.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService implements ICustomerTypeService {
    @Autowired
    private ICustomerTypeRepository iCustomerTypeRepository;

    @Override
    public List<CustomerType> findAll() {
        return iCustomerTypeRepository.findAll();
    }
}
