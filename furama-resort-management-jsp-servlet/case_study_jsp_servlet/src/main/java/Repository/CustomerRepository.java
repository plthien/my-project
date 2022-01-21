package Repository;

import bean.Customer;
import bean.CustomerType;
import bean.Employee;
import bean.EmployeeDegree;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();

    void save(Customer customer);

    void update(Customer customer);

    void remove(String id);

    List<Customer> findById(String id);

    List<Customer> findByName(String name);

    List<CustomerType> getCustomerType();
}
