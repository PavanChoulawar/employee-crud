package com.loginbackend.loginCrud.controller;



import com.loginbackend.loginCrud.model.Employee;
import com.loginbackend.loginCrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/list")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllemployees(){
        return employeeService.listOfEmployees();
    }


    // code for create employee Rest Api
    @PostMapping(value = "/", consumes = {"application/json"})
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }


    // Code for getting Employee details by id
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable long id){
        return  ResponseEntity.ok(employeeService.getEmployeesById(id));
    }

    // code for updating employees by id
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails){
        return employeeService.updateEmployee(id,employeeDetails);
    }


    // code for deleting employees by id
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        return employeeService.deleteEmployee(id);
    }
}

