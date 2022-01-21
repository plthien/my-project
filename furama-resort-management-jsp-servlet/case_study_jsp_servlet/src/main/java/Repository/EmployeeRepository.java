package Repository;

import bean.Employee;
import bean.EmployeeDegree;
import bean.EmployeeDepartment;
import bean.EmployeeOffice;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll();

    void save(Employee employee);

    void update(Employee employee);

    void remove(String id);

    List<Employee> findById(String id);

    List<Employee> findByName(String name);

    List<EmployeeDegree> getEmployeeDegree();

    List<EmployeeOffice> getEmployeeOffice();

    List<EmployeeDepartment> getEmployeeDepartment();
    }



