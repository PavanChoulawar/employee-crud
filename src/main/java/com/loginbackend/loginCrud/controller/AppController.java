package com.loginbackend.loginCrud.controller;

import com.loginbackend.loginCrud.model.Employee;
import com.loginbackend.loginCrud.model.User;
import com.loginbackend.loginCrud.repository.EmployeeRepository;
import com.loginbackend.loginCrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        List<Employee> listEmployees = employeeRepository.findAll();
        model.addAttribute("listEmployees", listEmployees);

        //return "employees";
        return "redirect:" + "http://localhost:4200/employees";
    }
}
