package com.codegym.furamaresortmanagement.service.employee;

import com.codegym.furamaresortmanagement.model.employee.Employee;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IEmployeeService {
    Page<Employee> findAllEmployee(int page, int size, String sortField, String sortDirection);

    Page<Employee> findEmployeeByKeyword(int page, int size, String sortField, String sortDirection, String keyword);


    Page<Employee> findEmployeeByOfficeId(int page, int size, String sortField, String sortDirection, Integer officeId);

    Employee findById(int id);

    void saveEmployee(Employee employee);

    void deleteEmployee(int id);

    List<Employee> findAll();

    Employee findEmployeeByEmail(String email);
}
