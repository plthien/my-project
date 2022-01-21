package com.codegym.furamaresortmanagement.repository.employee;

import com.codegym.furamaresortmanagement.model.employee.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, Integer> {
}
