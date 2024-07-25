package com.example.springboot.cruddemo.Service;

import com.example.springboot.cruddemo.DAO.EmployeeRepository;
import com.example.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    public EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {

        Optional<Employee> result = employeeRepository.findById(id);   // using optional to handle null values

        Employee theEmployee = null;
        if(result.isPresent()){
            theEmployee = result.get();
        }
        else{
            throw new RuntimeException("ID not found -" + id);
        }
        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Transactional
    @Override
    public void deleteByID(int id) {
        employeeRepository.deleteById(id);
    }
}
