package com.example.springboot.cruddemo.Rest;


import com.example.springboot.cruddemo.Service.EmployeeService;
import com.example.springboot.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeID}")
    public Employee getEmployee(@PathVariable int employeeID){
        Employee theEmployee = employeeService.findById(employeeID);

        if(theEmployee == null){
            throw new RuntimeException("Employee ID not found " + employeeID);
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee theEmployee){
        Employee newEmployee = employeeService.save(theEmployee);
        return newEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee updateEmp = employeeService.save(theEmployee);
        return updateEmp;
    }

    @DeleteMapping("/employees/{employeeID}")
    public String deleteEmployee(@PathVariable int employeeID){
        Employee tempEmployee = employeeService.findById(employeeID);

        if(tempEmployee == null){
            throw new RuntimeException("EMployee ID is not found in the DB -" + employeeID );
        }
        employeeService.deleteByID(employeeID);
        return ("Deleted employee id - " + employeeID);
    }

}
