package com.codegym.furamaresortmanagement.repository.employee;

import com.codegym.furamaresortmanagement.model.employee.EmployeeDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeDegreeRepository extends JpaRepository<EmployeeDegree, Integer> {
}
