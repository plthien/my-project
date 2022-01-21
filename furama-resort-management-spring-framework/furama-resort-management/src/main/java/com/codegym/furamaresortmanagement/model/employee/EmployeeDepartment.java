package com.codegym.furamaresortmanagement.model.employee;

import com.codegym.furamaresortmanagement.model.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "employee_department")
@Data @AllArgsConstructor @NoArgsConstructor
public class EmployeeDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "employeeDepartment")
    private List<Employee>employeeList;

}
