package com.codegym.furamaresortmanagement.controller;

import com.codegym.furamaresortmanagement.model.Person;
import com.codegym.furamaresortmanagement.model.employee.Employee;
import com.codegym.furamaresortmanagement.model.employee.EmployeeDegree;
import com.codegym.furamaresortmanagement.model.employee.EmployeeDepartment;
import com.codegym.furamaresortmanagement.model.employee.EmployeeOffice;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeDegreeService;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeDepartmentService;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeOfficeService;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeService;
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
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IEmployeeDepartmentService iEmployeeDepartmentService;

    @Autowired
    private IEmployeeDegreeService iEmployeeDegreeService;

    @Autowired
    private IEmployeeOfficeService iEmployeeOfficeService;

    @ModelAttribute("offices")
    public List<EmployeeOffice> offices() {
        return iEmployeeOfficeService.findAllOffice();
    }

    @ModelAttribute("degrees")
    public List<EmployeeDegree> degrees() {
        return iEmployeeDegreeService.findAllDegree();
    }

    @ModelAttribute("departments")
    public List<EmployeeDepartment> departments() {
        return iEmployeeDepartmentService.findAllDepartment();
    }

    @GetMapping()
    public String viewPageEmployee(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "sortField", defaultValue = "name") String sortField,
                                   @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
                                   Optional<String> keyword,
                                   Optional<Integer> officeId,
                                   Model model
    ) {
        int size = 5;
        Page<Employee> employeePage;
        if (!keyword.isPresent() || keyword.get().equals("")) {
            if (officeId.isPresent() && officeId.get() != 0) {
                employeePage = iEmployeeService.findEmployeeByOfficeId(page, size, sortField, sortDirection, officeId.get());
                model.addAttribute("officeId", officeId.get());
            } else {
                employeePage = iEmployeeService.findAllEmployee(page, size, sortField, sortDirection);
            }
        } else {
            employeePage = iEmployeeService.findEmployeeByKeyword(page, size, sortField, sortDirection, keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("employees", employeePage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        return "employee/list";
    }

    @GetMapping("/edit{id}")
    public ModelAndView showEditForm(@PathVariable("id") int id) {
        Employee employee = iEmployeeService.findById(id);

        return new ModelAndView("employee/edit", "employee", employee);

    }

    @PostMapping("/edit")
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            return "/employee/edit";
        }

        iEmployeeService.saveEmployee(employee);
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Edit employee successfully!");
        return "/employee/edit";

    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("employee/create", "employee", new Employee());
    }

    @PostMapping("/create")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            System.out.println(bindingResult.toString());
            return "/employee/create";
        }
        employee.setStatus(1);
        iEmployeeService.saveEmployee(employee);
        model.addAttribute("employee", new Employee());
        model.addAttribute("message", "Add new employee successfully!");
        return "/employee/create";
    }

    @GetMapping("/delete{id}")
    public String deleteEmployee(@PathVariable("id") int id,
                                 RedirectAttributes redirectAttributes){
        iEmployeeService.deleteEmployee(id);
        redirectAttributes.addFlashAttribute("message","Delete employee successfully!");
        return "redirect:/employees";


    }


}
