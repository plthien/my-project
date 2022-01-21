package com.codegym.furamaresortmanagement.model.employee;

import com.codegym.furamaresortmanagement.model.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "employee_office")
@Data @NoArgsConstructor @AllArgsConstructor
public class EmployeeOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "employeeOffice")
    private List<Employee> employeeList;

}
