package controller;

import bean.*;
import service.ContractService;
import service.CustomerService;
import service.EmployeeService;
import service.FacilityService;
import service.Impl.ContractServiceImpl;
import service.Impl.CustomerServiceImpl;
import service.Impl.EmployeeServiceImpl;
import service.Impl.FacilityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContractServlet" , urlPatterns = "/contracts")
public class ContractServlet extends HttpServlet {
    private ContractService contractService = new ContractServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private CustomerService customerService = new CustomerServiceImpl();
    private FacilityService facilityService = new FacilityServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userAction = request.getParameter("userAction");
        if (userAction == null) {
            userAction = "";
        }
        switch (userAction){
            case "create":
                createContract(request,response);
                break;
            case "edit":
                updateContract(request,response);
                break;
            case "search":
                searchContract(request,response);
                break;
            case "createDetail":
                createContractDetail(request,response);
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
                showCreateContractForm(request,response);
                break;
            case "edit":
                showEditContractForm(request,response);
                break;
            case "delete":
                deleteContract(request,response);
                break;
            case "contractDetail":
                contractDetailList(request,response);
                break;
            case "createDetail":
                showCreateContractDetailForm(request,response);
                break;
            case "deleteContractDetail":
                deleteContractDetail(request,response);
                break;
            default:
                contractList(request, response);
                break;

        }
    }
    public void contractList(HttpServletRequest request, HttpServletResponse response) {
        List<Contract> contractList = this.contractService.findAllContract();
        List<ContractDetail> contractDetailList = this.contractService.findAllContractDetail();
        request.setAttribute("contractList", contractList);
        request.setAttribute("contractDetailList", contractDetailList);
        try {
            request.getRequestDispatcher("pages/contract/contract-list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateContractForm(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customerList = this.customerService.findAll();
        List<Employee> employeeList =  this.employeeService.findAll();
        List<Facility> facilityList = this.facilityService.findAll();

        request.setAttribute("customerList", customerList);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("facilityList", facilityList);

        try {
            request.getRequestDispatcher("pages/contract/create-contract.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createContract(HttpServletRequest request, HttpServletResponse response) {
        String idCustomer = request.getParameter("inputIdCustomer");
        String idFacility = request.getParameter("inputIdFacility");
        String checkinDate = request.getParameter("inputCheckinDate");
        String checkoutDate = request.getParameter("inputCheckoutDate");
        Double deposit = Double.parseDouble(request.getParameter("inputDeposit")) ;
        Double payment = Double.parseDouble(request.getParameter("inputPayment")) ;
        String idEmployee= request.getParameter("inputIdEmployee");

        Customer customer = new Customer();
        Employee employee = new Employee();
        Facility facility = new Facility();
        Contract contract = new Contract();

        employee.setId(idEmployee);
        customer.setId(idCustomer);
        facility.setId(idFacility);
        contract.setCheckInDate(checkinDate);
        contract.setCheckOutDate(checkoutDate);
        contract.setDeposit(deposit);
        contract.setPayment(payment);
        contract.setEmployee(employee);
        contract.setCustomer(customer);
        contract.setFacility(facility);

        this.contractService.saveContract(contract);
        try {
            response.sendRedirect("/contracts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showEditContractForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        List<Contract> contractList = this.contractService.findById(id);
        List<Customer> customerList = this.customerService.findAll();
        List<Employee> employeeList =  this.employeeService.findAll();
        List<Facility> facilityList = this.facilityService.findAll();

        request.setAttribute("contractList", contractList);
        request.setAttribute("customerList", customerList);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("facilityList", facilityList);
        try {
            request.getRequestDispatcher("/pages/contract/edit-contract.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateContract(HttpServletRequest request, HttpServletResponse response) {
        String idContract = request.getParameter("inputIDContract");
        String idCustomer = request.getParameter("inputIdCustomer");
        String idFacility = request.getParameter("inputIdFacility");
        String checkinDate = request.getParameter("inputCheckinDate");
        String checkoutDate = request.getParameter("inputCheckoutDate");
        Double deposit = Double.parseDouble(request.getParameter("inputDeposit")) ;
        Double payment = Double.parseDouble(request.getParameter("inputPayment")) ;
        String idEmployee= request.getParameter("inputIdEmployee");

        Customer customer = new Customer();
        Employee employee = new Employee();
        Facility facility = new Facility();
        Contract contract = new Contract();

        employee.setId(idEmployee);
        customer.setId(idCustomer);
        facility.setId(idFacility);
        contract.setId(idContract);
        contract.setCheckInDate(checkinDate);
        contract.setCheckOutDate(checkoutDate);
        contract.setDeposit(deposit);
        contract.setPayment(payment);
        contract.setEmployee(employee);
        contract.setCustomer(customer);
        contract.setFacility(facility);

        this.contractService.updateContract(contract);
        try {
            response.sendRedirect("/contracts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteContract(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        this.contractService.remove(id);
        try {
            response.sendRedirect("/contracts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchContract(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("searchID");
        List<Contract> contractList = this.contractService.findById(id);
        List<ContractDetail> contractDetailList = this.contractService.findAllContractDetail();

        request.setAttribute("contractDetailList", contractDetailList);
        request.setAttribute("contractList", contractList);
        try {
            request.getRequestDispatcher("/pages/contract/contract-list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void contractDetailList(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String idContract = request.getParameter("idContract");

        List<ContractDetail> contractDetailList = this.contractService.findContractDetailById(id);

        request.setAttribute("contractDetailList",contractDetailList);
        request.setAttribute("idContract",idContract);
        try {
            request.getRequestDispatcher("/pages/contract/contract-detail.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void showCreateContractDetailForm(HttpServletRequest request, HttpServletResponse response){
        String idContract = request.getParameter("id");

        List<ExtraService> extraServiceList = this.contractService.getExtraService();

        request.setAttribute("idContract",idContract);
        request.setAttribute("extraServiceList",extraServiceList);

        try {
            request.getRequestDispatcher("/pages/contract/create-contract-detail.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createContractDetail(HttpServletRequest request, HttpServletResponse response){
        String iDContract = request.getParameter("inputIDContract");
        String iDExtraService = request.getParameter("inputIDExtraService");
        int quantity =Integer.parseInt(request.getParameter("inputQuantity")) ;

        Contract contract = new Contract();
        contract.setId(iDContract);

        ExtraService extraService = new ExtraService();
        extraService.setId(iDExtraService);

        ContractDetail contractDetail = new ContractDetail();
        contractDetail.setContract(contract);
        contractDetail.setExtraService(extraService);
        contractDetail.setQuantity(quantity);

        this.contractService.saveContractDetail(contractDetail);

        try {
            response.sendRedirect("/contracts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteContractDetail(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        this.contractService.removeContractDetail(id);

        try {
            response.sendRedirect("/contracts");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
