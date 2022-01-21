package com.codegym.furamaresortmanagement.service.customer.impl;

import com.codegym.furamaresortmanagement.model.customer.Customer;
import com.codegym.furamaresortmanagement.repository.customer.ICustomerRepository;
import com.codegym.furamaresortmanagement.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Page<Customer> findCustomerByCustomerTypeId(int page, int size, String sortField, String sortDirection, Integer customerTypeId) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        return iCustomerRepository.findAllByCustomerTypeId(pageable, customerTypeId);
    }

    @Override
    public Page<Customer> findAllCustomer(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        return iCustomerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findCustomerByKeyword(int page, int size, String sortField, String sortDirection, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        return iCustomerRepository.findAllByKeyword(pageable,keyword);
    }

    @Override
    public Customer findOneById(String id) {
        return iCustomerRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCustomer(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String id) {
        iCustomerRepository.removeCustomerById(id);
    }

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }
}
