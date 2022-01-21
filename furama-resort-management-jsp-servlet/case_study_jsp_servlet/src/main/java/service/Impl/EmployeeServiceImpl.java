package service.Impl;

import Repository.EmployeeRepository;
import Repository.Impl.EmployeeRepositoryImpl;
import bean.Employee;
import bean.EmployeeDegree;
import bean.EmployeeDepartment;
import bean.EmployeeOffice;
import service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }

    @Override
    public void remove(String id) {
        employeeRepository.remove(id);
    }

    @Override
    public List<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public List<EmployeeDegree> getEmployeeDegree() {
        return employeeRepository.getEmployeeDegree();
    }

    @Override
    public List<EmployeeOffice> getEmployeeOffice() {
        return employeeRepository.getEmployeeOffice();
    }

    @Override
    public List<EmployeeDepartment> getEmployeeDepartment() {
        return employeeRepository.getEmployeeDepartment();
    }
}
