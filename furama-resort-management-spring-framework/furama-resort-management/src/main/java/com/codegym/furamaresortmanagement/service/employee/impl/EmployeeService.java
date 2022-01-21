package com.codegym.furamaresortmanagement.service.employee.impl;

import com.codegym.furamaresortmanagement.model.Person;
import com.codegym.furamaresortmanagement.model.employee.Employee;
import com.codegym.furamaresortmanagement.repository.employee.IEmployeeRepository;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeService;
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
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Page<Employee> findAllEmployee(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return iEmployeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findEmployeeByKeyword(int page, int size, String sortField, String sortDirection, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return iEmployeeRepository.findEmployeeByKeyword(pageable,keyword);
    }


    @Override
    public Page<Employee> findEmployeeByOfficeId(int page, int size, String sortField, String sortDirection, Integer officeId) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return  iEmployeeRepository.findAllByEmployeeOffice_Id(pageable,officeId);
    }

    @Override
    public Employee findById(int id) {
        return iEmployeeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmployee(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        iEmployeeRepository.removeEmployeeById(id);
    }

    @Override
    public List<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return iEmployeeRepository.findByEmailEquals(email);
    }

}
