package com.codegym.furamaresortmanagement.controller;

import com.codegym.furamaresortmanagement.model.employee.Employee;
import com.codegym.furamaresortmanagement.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserLoginController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @GetMapping("/login")
    public String showLoginForm(){
        return "home/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal){

        String email = principal.getName();
        Employee employee = iEmployeeService.findEmployeeByEmail(email);
        model.addAttribute("employee",employee);
        return "home/profile";
    }
}