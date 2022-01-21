package controller;

import bean.Employee;
import bean.EmployeeDegree;
import bean.EmployeeDepartment;
import bean.EmployeeOffice;
import service.EmployeeService;
import service.Impl.EmployeeServiceImpl;
import utils.Validate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FuramaServlet", urlPatterns = {"/employees"})
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userAction = request.getParameter("userAction");
        if (userAction == null) {
            userAction = "";
        }
        switch (userAction) {
            case "create":
                createEmployee(request, response);
                break;
            case "edit":
                updateEmployee(request, response);
                break;
            case "search":
                searchEmployee(request, response);
                break;
            default:
                break;


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userAction = request.getParameter("userAction");
        if (userAction == null) {
            userAction = "";
        }
        switch (userAction) {
            case "create":
                showCreateEmployeeForm(request, response);
                break;
            case "edit":
                showEditEmployeeForm(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            case "search":
                searchEmployee(request, response);
                break;
            default:
                employeeList(request, response);
                break;

        }
    }

    public void employeeList(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employeeList = this.employeeService.findAll();
        request.setAttribute("employeeList", employeeList);
        try {
            request.getRequestDispatcher("pages/employee/employee-list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateEmployeeForm(HttpServletRequest request, HttpServletResponse response) {
        List<EmployeeDegree> employeeDegreeList = this.employeeService.getEmployeeDegree();
        List<EmployeeOffice> employeeOfficeList = this.employeeService.getEmployeeOffice();
        List<EmployeeDepartment> employeeDepartmentList = this.employeeService.getEmployeeDepartment();

        request.setAttribute("employeeDegreeList", employeeDegreeList);
        request.setAttribute("employeeOfficeList", employeeOfficeList);
        request.setAttribute("employeeDepartmentList", employeeDepartmentList);
        try {
            request.getRequestDispatcher("pages/employee/create-employee.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createEmployee(HttpServletRequest request, HttpServletResponse response) {
        String personalIDMess = null;
        String phoneNumberMess = null;
        String salaryMess = null;
        String emailMess = null;
        boolean flag = true;

        String name = request.getParameter("input-name");
        String birthday = request.getParameter("input-birthday");
        String gender = request.getParameter("input-gender");
        String personalID = request.getParameter("input-personal-id");
        String phoneNumber = request.getParameter("input-phone-number");
        String address = request.getParameter("input-address");
        String email = request.getParameter("input-email");
        Double salary = 0d;
        try {
            salary = Double.parseDouble(request.getParameter("input-salary"));

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            salaryMess = "Salary is invalid!";
            flag = false;
        }

        int degreeID = Integer.parseInt(request.getParameter("input-degree"));
        int officeID = Integer.parseInt(request.getParameter("input-office"));
        int departmentID = Integer.parseInt(request.getParameter("input-department"));



        if (!Validate.checkPersonalID(personalID)) {
            personalIDMess = "Personal ID is invalid!";
            flag = false;
        }
        if (!Validate.checkPhoneNumber(phoneNumber)) {
            phoneNumberMess = "Phone Number is invalid!";
            flag = false;
        }
        if (salary < 0 || salary.isNaN()) {
            salaryMess = "Salary is invalid!";
            flag = false;
        }
        if (!Validate.checkEmail(email)) {
            emailMess = "Email is invalid!";
            flag = false;
        }

        Employee employee = new Employee();
        EmployeeDegree employeeDegree = new EmployeeDegree();
        EmployeeOffice employeeOffice = new EmployeeOffice();
        EmployeeDepartment employeeDepartment = new EmployeeDepartment();
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setGender(gender);
        employee.setPersonalID(personalID);
        employee.setPhoneNumber(phoneNumber);
        employee.setAddress(address);
        employee.setSalary(salary);
        employee.setEmail(email);

        employeeDegree.setId(degreeID);
        employeeOffice.setId(officeID);
        employeeDepartment.setId(departmentID);

        employee.setEmployeeDegree(employeeDegree);
        employee.setEmployeeOffice(employeeOffice);
        employee.setEmployeeDepartment(employeeDepartment);

        if (!flag) {
            request.setAttribute("personalIDMess", personalIDMess);
            request.setAttribute("phoneNumberMess", phoneNumberMess);
            request.setAttribute("salaryMess", salaryMess);
            request.setAttribute("emailMess", emailMess);
            request.setAttribute("employee", employee);
            showCreateEmployeeForm(request, response);
        } else {
            this.employeeService.save(employee);
            try {
                response.sendRedirect("/employees");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void showEditEmployeeForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        List<Employee> employeeList = this.employeeService.findById(id);
        List<EmployeeDegree> employeeDegreeList = this.employeeService.getEmployeeDegree();
        List<EmployeeOffice> employeeOfficeList = this.employeeService.getEmployeeOffice();
        List<EmployeeDepartment> employeeDepartmentList = this.employeeService.getEmployeeDepartment();

        request.setAttribute("employeeDegreeList", employeeDegreeList);
        request.setAttribute("employeeOfficeList", employeeOfficeList);
        request.setAttribute("employeeDepartmentList", employeeDepartmentList);
        request.setAttribute("employeeList", employeeList);
        try {
            request.getRequestDispatcher("/pages/employee/edit-employee.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
        String personalIDMess = null;
        String phoneNumberMess = null;
        String salaryMess = null;
        String emailMess = null;
        boolean flag = true;

        String id = request.getParameter("input-id");
        String name = request.getParameter("input-name");
        String birthday = request.getParameter("input-birthday");
        String gender = request.getParameter("input-gender");
        String personalID = request.getParameter("input-personal-id");
        String phoneNumber = request.getParameter("input-phone-number");
        String address = request.getParameter("input-address");
        String email = request.getParameter("input-email");
        Double salary = 0d;

        try {
            salary = Double.parseDouble(request.getParameter("input-salary"));

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            salaryMess = "Salary is invalid!";
            flag = false;
        }

        int degreeID = Integer.parseInt(request.getParameter("input-degree"));
        int officeID = Integer.parseInt(request.getParameter("input-office"));
        int departmentID = Integer.parseInt(request.getParameter("input-department"));


        if (!Validate.checkPersonalID(personalID)) {
            personalIDMess = "Personal ID is invalid!";
            flag = false;
        }
        if (!Validate.checkPhoneNumber(phoneNumber)) {
            phoneNumberMess = "Phone Number is invalid!";
            flag = false;
        }
        if (salary < 0 || salary.isNaN()) {
            salaryMess = "Salary is invalid!";
            flag = false;
        }
        if (!Validate.checkEmail(email)) {
            emailMess = "Email is invalid!";
            flag = false;
        }

        EmployeeDegree employeeDegree = new EmployeeDegree();
        EmployeeOffice employeeOffice = new EmployeeOffice();
        EmployeeDepartment employeeDepartment = new EmployeeDepartment();
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setGender(gender);
        employee.setPersonalID(personalID);
        employee.setPhoneNumber(phoneNumber);
        employee.setAddress(address);
        employee.setSalary(salary);
        employee.setEmail(email);

        employeeDegree.setId(degreeID);
        employeeOffice.setId(officeID);
        employeeDepartment.setId(departmentID);

        employee.setEmployeeDegree(employeeDegree);
        employee.setEmployeeOffice(employeeOffice);
        employee.setEmployeeDepartment(employeeDepartment);

        if (!flag) {
            request.setAttribute("personalIDMess", personalIDMess);
            request.setAttribute("phoneNumberMess", phoneNumberMess);
            request.setAttribute("salaryMess", salaryMess);
            request.setAttribute("emailMess", emailMess);
            request.setAttribute("employee", employee);
            showEditEmployeeForm(request, response);
        } else {
            this.employeeService.update(employee);
            try {
                response.sendRedirect("/employees");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        this.employeeService.remove(id);
        try {
            response.sendRedirect("/employees");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchEmployee(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("searchName");
        List<Employee> employeeList = this.employeeService.findById(search);

        if (employeeList.isEmpty()) {
            employeeList = this.employeeService.findByName(search);

        }
        request.setAttribute("employeeList", employeeList);
        try {
            request.getRequestDispatcher("/pages/employee/employee-list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
