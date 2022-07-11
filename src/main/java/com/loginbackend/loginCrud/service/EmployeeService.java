package com.loginbackend.loginCrud.service;

import com.loginbackend.loginCrud.exception.ResourceNotFoundException;
import com.loginbackend.loginCrud.model.Employee;
import com.loginbackend.loginCrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * This method returns employee details based on id
     * @param id
     * @return
     */
    public Employee getEmployeesById(long  id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with this id does not exist"));
        return employee;
    }

    /**
     * This method returns list of employees
     * @return
     */
    public List<Employee> listOfEmployees(){
        List<Employee> listofEmployees = employeeRepository.findAll();
        return listofEmployees;
    }


    /**
     * This method inserts employee into database
     * @param employee
     * @return
     */
    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }


    /**
     * This method updates existing data with new data by id
     * @param id
     * @param employee
     * @return
     */
    public ResponseEntity<Employee> updateEmployee(long  id, Employee employee){
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with this id does not exist: " + id));

        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getLastName());
        updateEmployee.setEmailId(employee.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }


    /**
     * this method deletes the data
     * @param id
     * @return
     */
    public ResponseEntity<HttpStatus> deleteEmployee(long  id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with this id does not exist"));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

