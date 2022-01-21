package com.codegym.furamaresortmanagement.service.employee.impl;

import com.codegym.furamaresortmanagement.model.employee.EmployeeDepartment;
import com.codegym.furamaresortmanagement.repository.employee.IEmployeeDepartmentRepository;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDepartmentService implements IEmployeeDepartmentService {
    @Autowired
    private IEmployeeDepartmentRepository iEmployeeDepartmentRepository;

    @Override
    public List<EmployeeDepartment> findAllDepartment() {
        return iEmployeeDepartmentRepository.findAll();
    }
}
