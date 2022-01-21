package com.codegym.furamaresortmanagement.service.employee.impl;

import com.codegym.furamaresortmanagement.model.employee.EmployeeOffice;
import com.codegym.furamaresortmanagement.repository.employee.IEmployeeOfficeRepository;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeOfficeService implements IEmployeeOfficeService {

    @Autowired
    private IEmployeeOfficeRepository iEmployeeOfficeRepository;

    @Override
    public List<EmployeeOffice> findAllOffice() {
        return iEmployeeOfficeRepository.findAllOffice();
    }
}
