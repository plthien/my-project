package bean;

public class Employee extends Person {
    private String id;
    private double salary;
    private EmployeeOffice employeeOffice;
    private EmployeeDegree employeeDegree;
    private EmployeeDepartment employeeDepartment;
    private User user;

    public Employee() {
    }

    public Employee(String name, String birthday, String gender, String personalID, String phoneNumber, String email, String address, String id, double salary, EmployeeOffice employeeOffice, EmployeeDegree employeeDegree, EmployeeDepartment employeeDepartment, User user) {
        super(name, birthday, gender, personalID, phoneNumber, email, address);
        this.id = id;
        this.salary = salary;
        this.employeeOffice = employeeOffice;
        this.employeeDegree = employeeDegree;
        this.employeeDepartment = employeeDepartment;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
