package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(int id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }

    public Employee save(Employee users) {
        return employeeRepository.save(users);
    }

    public void insert(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            employeeRepository.save(employee);
        }
    }

    public void deleteById(int id) {
        boolean exists = employeeRepository.existsById(id);
        if (exists) {
            employeeRepository.deleteById(id);
        } else {
            throw  new IllegalArgumentException("Invalid id!");
        }
    }

    public boolean existEmployeeById(int id) {
        return employeeRepository.existsById(id);
    }
}
