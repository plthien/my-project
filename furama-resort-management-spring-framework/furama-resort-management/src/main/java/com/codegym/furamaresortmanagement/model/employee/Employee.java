package com.codegym.furamaresortmanagement.model.employee;

import com.codegym.furamaresortmanagement.model.*;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity(name = "employees")
public class Employee extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Min(0)
    private double salary;
    @ManyToOne(targetEntity = EmployeeOffice.class)
    private EmployeeOffice employeeOffice;
    @ManyToOne(targetEntity = EmployeeDegree.class)
    private EmployeeDegree employeeDegree;
    @ManyToOne(targetEntity = EmployeeDepartment.class)
    private EmployeeDepartment employeeDepartment;
    @OneToOne(targetEntity = AppUser.class)
    private AppUser appUser;

    public Employee() {
    }

    public Employee(@NotBlank String name, @NotNull LocalDate birthday, @NotBlank String gender, @Pattern(regexp = "(^\\d{9}$)||(^\\d{12}$)") @NotBlank String personalID, @Pattern(regexp = "(^09[01]\\d{7}$)||(\\(^84\\)\\+9[01]\\d{7}$)") @NotBlank String phoneNumber, @NotBlank @Pattern(regexp = "[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)") String email, @NotBlank String address, int status, int id, @NotNull @Min(0) double salary, EmployeeOffice employeeOffice, EmployeeDegree employeeDegree, EmployeeDepartment employeeDepartment, AppUser appUser) {
        super(name, birthday, gender, personalID, phoneNumber, email, address, status);
        this.id = id;
        this.salary = salary;
        this.employeeOffice = employeeOffice;
        this.employeeDegree = employeeDegree;
        this.employeeDepartment = employeeDepartment;
        this.appUser = appUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public EmployeeOffice getEmployeeOffice() {
        return employeeOffice;
    }

    public void setEmployeeOffice(EmployeeOffice employeeOffice) {
        this.employeeOffice = employeeOffice;
    }

    public EmployeeDegree getEmployeeDegree() {
        return employeeDegree;
    }

    public void setEmployeeDegree(EmployeeDegree employeeDegree) {
        this.employeeDegree = employeeDegree;
    }

    public EmployeeDepartment getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(EmployeeDepartment employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
