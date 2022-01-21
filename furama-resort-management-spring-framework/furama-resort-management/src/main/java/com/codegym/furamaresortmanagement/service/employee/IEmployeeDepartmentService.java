package com.codegym.furamaresortmanagement.service.employee;

import com.codegym.furamaresortmanagement.model.employee.EmployeeDepartment;

import java.util.List;

public interface IEmployeeDepartmentService {
    List<EmployeeDepartment> findAllDepartment();
}
