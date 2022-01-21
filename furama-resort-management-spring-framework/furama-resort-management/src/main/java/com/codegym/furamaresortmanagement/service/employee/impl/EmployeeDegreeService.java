package com.codegym.furamaresortmanagement.service.employee.impl;

import com.codegym.furamaresortmanagement.model.employee.EmployeeDegree;
import com.codegym.furamaresortmanagement.repository.employee.IEmployeeDegreeRepository;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDegreeService implements IEmployeeDegreeService {
    @Autowired
    private IEmployeeDegreeRepository iEmployeeDegreeRepository;

    @Override
    public List<EmployeeDegree> findAllDegree() {
        return iEmployeeDegreeRepository.findAll();
    }
}
