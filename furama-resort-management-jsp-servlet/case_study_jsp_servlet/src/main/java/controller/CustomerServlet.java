package controller;

import bean.*;
import service.CustomerService;
import service.Impl.CustomerServiceImpl;
import utils.Validate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customers"})
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userAction = request.getParameter("userAction");
        if (userAction == null) {
            userAction = "";
        }
        switch (userAction) {
            case "create":
                createCustomer(request, response);
                break;
            case "edit":
                updateCustomer(request, response);
                break;
            case "search":
                searchCustomer(request, response);
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
                showCreateCustomerForm(request, response);
                break;
            case "edit":
                showEditCustomerForm(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            case "search":
                searchCustomer(request, response);
                break;
            default:
                customerList(request, response);
                break;

        }
    }

    public void customerList(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customerList = this.customerService.findAll();
        request.setAttribute("customerList", customerList);
        try {
            request.getRequestDispatcher("pages/customer/customer-list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateCustomerForm(HttpServletRequest request, HttpServletResponse response) {
        List<CustomerType> customerTypeList = this.customerService.getCustomerType();

        request.setAttribute("customerTypeList", customerTypeList);

        try {
            request.getRequestDispatcher("pages/customer/create-customer.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        String personalIDMess = null;
        String phoneNumberMess = null;
        String emailMess = null;
        String idMess = null;
        boolean flag = true;

        String id = request.getParameter("input-id");
        String name = request.getParameter("input-name");
        String birthday = request.getParameter("input-birthday");
        String gender = request.getParameter("input-gender");
        String personalID = request.getParameter("input-personal-id");
        String phoneNumber = request.getParameter("input-phone-number");
        String address = request.getParameter("input-address");
        String email = request.getParameter("input-email");

        int customerTypeID = Integer.parseInt(request.getParameter("input-customerType"));

        if (!Validate.checkPersonalID(personalID)) {
            personalIDMess = "Personal ID is invalid!";
            flag = false;
        }
        if (!Validate.checkPhoneNumber(phoneNumber)) {
            phoneNumberMess = "Phone Number is invalid!";
            flag = false;
        }

        if (!Validate.checkEmail(email)) {
            emailMess = "Email is invalid!";
            flag = false;
        }

        if (!Validate.checkIDCustomer(id)) {
            idMess = "Id is invalid!";
            flag = false;
        }

        Customer customer = new Customer();
        CustomerType customerType = new CustomerType();

        customer.setId(id);
        customer.setName(name);
        customer.setBirthday(birthday);
        customer.setGender(gender);
        customer.setPersonalID(personalID);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        customer.setEmail(email);

        customerType.setId(customerTypeID);

        customer.setCustomerType(customerType);

        if (!flag) {
            request.setAttribute("personalIDMess", personalIDMess);
            request.setAttribute("phoneNumberMess", phoneNumberMess);
            request.setAttribute("emailMess", emailMess);
            request.setAttribute("idMess", idMess);
            request.setAttribute("customer", customer);
            showCreateCustomerForm(request, response);
        } else {
            this.customerService.save(customer);
            try {
                response.sendRedirect("/customers");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void showEditCustomerForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        List<Customer> customerList = this.customerService.findById(id);

        List<CustomerType> customerTypeList = this.customerService.getCustomerType();

        request.setAttribute("customerTypeList", customerTypeList);
        request.setAttribute("customerList", customerList);
        try {
            request.getRequestDispatcher("/pages/customer/edit-customer.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
        String phoneNumberMess = null;
        String emailMess = null;
        String personalIDMess = null;
        boolean flag = true;

        String id = request.getParameter("input-id");
        String name = request.getParameter("input-name");
        String birthday = request.getParameter("input-birthday");
        String gender = request.getParameter("input-gender");
        String personalID = request.getParameter("input-personal-id");
        String phoneNumber = request.getParameter("input-phone-number");
        String address = request.getParameter("input-address");
        String email = request.getParameter("input-email");

        int customerTypeID = Integer.parseInt(request.getParameter("input-customerType"));

        if (!Validate.checkPersonalID(personalID)) {
            personalIDMess = "Personal ID is invalid!";
            flag = false;
        }
        if (!Validate.checkPhoneNumber(phoneNumber)) {
            phoneNumberMess = "Phone Number is invalid!";
            flag = false;
        }

        if (!Validate.checkEmail(email)) {
            emailMess = "Email is invalid!";
            flag = false;
        }

        CustomerType customerType = new CustomerType();
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setBirthday(birthday);
        customer.setGender(gender);
        customer.setPersonalID(personalID);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        customer.setEmail(email);

        customerType.setId(customerTypeID);

        customer.setCustomerType(customerType);

        if (!flag) {
            request.setAttribute("personalIDMess", personalIDMess);
            request.setAttribute("phoneNumberMess", phoneNumberMess);
            request.setAttribute("emailMess", emailMess);
            request.setAttribute("customer", customer);
            showEditCustomerForm(request, response);
        } else {
            this.customerService.update(customer);
            try {
                response.sendRedirect("/customers");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        this.customerService.remove(id);
        try {
            response.sendRedirect("/customers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchCustomer(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("searchName");
        List<Customer> customerList = this.customerService.findById(search);

        if (customerList.isEmpty()) {
            customerList = this.customerService.findByName(search);
        }
        request.setAttribute("customerList", customerList);
        try {
            request.getRequestDispatcher("/pages/customer/customer-list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
