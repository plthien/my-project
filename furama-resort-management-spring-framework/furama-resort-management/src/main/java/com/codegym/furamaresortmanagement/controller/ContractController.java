package com.codegym.furamaresortmanagement.controller;

import com.codegym.furamaresortmanagement.model.contract.Contract;
import com.codegym.furamaresortmanagement.model.contract.ContractDetail;
import com.codegym.furamaresortmanagement.model.contract.ExtraService;
import com.codegym.furamaresortmanagement.model.customer.Customer;
import com.codegym.furamaresortmanagement.model.employee.Employee;
import com.codegym.furamaresortmanagement.model.facility.Facility;
import com.codegym.furamaresortmanagement.service.contract.IContractDetailService;
import com.codegym.furamaresortmanagement.service.contract.IContractService;
import com.codegym.furamaresortmanagement.service.contract.IExtraServiceService;
import com.codegym.furamaresortmanagement.service.customer.ICustomerService;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeService;
import com.codegym.furamaresortmanagement.service.facility.IFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private IContractService iContractService;

    @Autowired
    private IContractDetailService iContractDetailService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IFacilityService iFacilityService;

    @Autowired
    private IExtraServiceService iExtraServiceService;


    @ModelAttribute("contractDetails")
    public List<ContractDetail> contractDetails() {
        return iContractDetailService.findAll();
    }

    @ModelAttribute("customers")
    public List<Customer> customers() {
        return iCustomerService.findAll();
    }

    @ModelAttribute("employees")
    public List<Employee> employees() {
        return iEmployeeService.findAll();
    }

    @ModelAttribute("facilities")
    public List<Facility> facilities() {
        return iFacilityService.findAll();
    }

    @ModelAttribute("extraServices")
    public List<ExtraService> extraServices() {
        return iExtraServiceService.findAll();
    }


    @GetMapping()
    public String viewPageContract(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                   @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
                                   Optional<String> keyword,
                                   @RequestParam(value = "dateSearch", defaultValue = "2000-01-01")
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSearch,
                                   Model model
    ) {
        int size = 5;
        Page<Contract> contractPage;
        if (!keyword.isPresent() || keyword.get().equals("")) {
            if (dateSearch != null && !dateSearch.isEqual(LocalDate.of(2000, 1, 1))) {
                contractPage = iContractService.findContractByDate(page, size, sortField, sortDirection, dateSearch);
                model.addAttribute("dateSearch", dateSearch);
            } else {
                contractPage = iContractService.findAllContract(page, size, sortField, sortDirection);
            }
        } else {
            contractPage = iContractService.findContractByCustomer(page, size, sortField, sortDirection, keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("contracts", contractPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        return "contract/list";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("contract/create", "contract", new Contract());
    }

    @PostMapping("/create")
    public String saveContract(@Valid @ModelAttribute("contract") Contract contract,
                               BindingResult bindingResult,
                               Model model) {
        new Contract().validate(contract, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("contract", contract);
            System.out.println(bindingResult.toString());
            return "/contract/create";
        }
        contract.setStatus(1);
        iContractService.saveContract(contract);
        model.addAttribute("contract", new Contract());
        model.addAttribute("message", "Add new Contract successfully!");
        return "/contract/create";
    }

    @GetMapping("/createContractDetails{id}")
    public String showCreateContractDetailsForm(@PathVariable("id") Long id, Model model) {
        ContractDetail contractDetail = new ContractDetail();
        Contract contract = iContractService.findContractById(id);
        contractDetail.setContract(contract);
        model.addAttribute("contractDetail", contractDetail);
        return "contract/createContractDetails";

    }

    @PostMapping("/createContractDetails")
    public String saveContractDetail(@ModelAttribute("contractDetail") ContractDetail contractDetail, Model model) {
        iContractDetailService.saveContractDetail(contractDetail);
        model.addAttribute("message", "Create Contract Detail Successfully!");
        ContractDetail contractDetail2 = new ContractDetail();
        contractDetail2.setContract(contractDetail.getContract());
        model.addAttribute("contractDetail", contractDetail2);
        return "contract/createContractDetails";

    }

    @PostMapping("/editContractDetails")
    public String updateContractDetails(@ModelAttribute("contractDetail") ContractDetail contractDetail, Model model) {
        iContractDetailService.saveContractDetail(contractDetail);
        model.addAttribute("message", "Edit Contract Detail Successfully!");

        model.addAttribute("contractDetail", contractDetail);
        return "contract/contractDetail";

    }

    @GetMapping("/contractDetails{id}")
    public String showContractDetail(@PathVariable("id") Long id, Model model) {
        ContractDetail contractDetail = iContractDetailService.findOne(id);
        model.addAttribute("contractDetail", contractDetail);
        return "/contract/contractDetail";
    }

    @GetMapping("/edit{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Contract contract = iContractService.findContractById(id);
        return new ModelAndView("contract/edit", "contract", contract);

    }

    @PostMapping("/edit")
    public String updateEmployee(@Valid @ModelAttribute("contract") Contract contract,
                                 BindingResult bindingResult,
                                 Model model) {
        new Contract().validate(contract, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("contract", contract);
            System.out.println(bindingResult.toString());
            return "/contract/edit";
        }
        contract.setStatus(1);
        iContractService.saveContract(contract);
        model.addAttribute("contract", contract);
        model.addAttribute("message", "Edit Contract successfully!");
        return "/contract/edit";

    }

    @GetMapping("/delete{id}")
    public String deleteCustomer(@PathVariable("id") Long id,
                                 RedirectAttributes redirectAttributes){
        iContractService.deleteContract(id);
        redirectAttributes.addFlashAttribute("message","Delete Contract successfully!");
        return "redirect:/contracts";

    }


}

