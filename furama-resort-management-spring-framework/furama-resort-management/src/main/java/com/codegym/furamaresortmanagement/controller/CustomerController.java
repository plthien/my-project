package com.codegym.furamaresortmanagement.controller;

import com.codegym.furamaresortmanagement.model.customer.Customer;
import com.codegym.furamaresortmanagement.model.customer.CustomerType;
import com.codegym.furamaresortmanagement.model.employee.Employee;
import com.codegym.furamaresortmanagement.service.customer.ICustomerService;
import com.codegym.furamaresortmanagement.service.customer.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private ICustomerTypeService iCustomerTypeService;

    @ModelAttribute("customerType")
    public List<CustomerType> customerTypes(){
        return iCustomerTypeService.findAll();
    }

    @GetMapping()
    public String viewPageCustomer(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "sortField", defaultValue = "name") String sortField,
                                   @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
                                   Optional<String> keyword,
                                   Optional<Integer> customerTypeId,
                                   Model model
    ) {
        int size = 5;
        Page<Customer> customerPage;
        if (!keyword.isPresent() || keyword.get().equals("")) {
            if (customerTypeId.isPresent() && customerTypeId.get() != 0) {
                customerPage = iCustomerService.findCustomerByCustomerTypeId(page, size, sortField, sortDirection, customerTypeId.get());
                model.addAttribute("customerTypeId", customerTypeId.get());
            } else {
                customerPage = iCustomerService.findAllCustomer(page, size, sortField, sortDirection);
            }
        } else {
            customerPage = iCustomerService.findCustomerByKeyword(page, size, sortField, sortDirection, keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("customers", customerPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        return "customer/list";
    }

    @GetMapping("/edit{id}")
    public ModelAndView showEditForm(@PathVariable("id") String id) {
        Customer customer = iCustomerService.findOneById(id);

        return new ModelAndView("customer/edit", "customer", customer);

    }

    @PostMapping("/edit")
    public String updateEmployee(@Valid @ModelAttribute("customer") Customer customer,
                                 BindingResult bindingResult,
                                 Model model) {
        new Customer().validate(customer,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customer);
            return "/customer/edit";
        }

        iCustomerService.saveCustomer(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("message", "Edit customer successfully!");
        return "/customer/edit";

    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("customer/create", "customer", new Customer());
    }

    @PostMapping("/create")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,
                               BindingResult bindingResult,
                               Model model) {
        new Customer().validate(customer,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customer);
            System.out.println(bindingResult.toString());
            return "/customer/create";
        }
        customer.setStatus(1);
        iCustomerService.saveCustomer(customer);
        model.addAttribute("customer", new Customer());
        model.addAttribute("message", "Add new customer successfully!");
        return "/customer/create";
    }

    @GetMapping("/delete{id}")
    public String deleteCustomer(@PathVariable("id") String id,
                                 RedirectAttributes redirectAttributes){
        iCustomerService.deleteCustomer(id);
        redirectAttributes.addFlashAttribute("message","Delete Customer successfully!");
        return "redirect:/customers";


    }
}
