package com.example.fedmen.services;

import com.example.fedmen.domains.Employee;
import com.example.fedmen.domains.dto.EmployeeRequestDto;
import com.example.fedmen.domains.dto.EmployeeResponseDto;
import com.example.fedmen.domains.dto.update.UpdateEmployeeRequestDto;
import com.example.fedmen.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class EmployeeService {
    EmployeeRepository employeeRepository;
    public List<EmployeeResponseDto> findAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResponseDto> employeeResponse = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeResponseDto newEmployee = new EmployeeResponseDto();
            newEmployee.setName(employee.getName());
            newEmployee.setEmail(employee.getEmail());
            employeeResponse.add(newEmployee);
        }
        return employeeResponse;
    }

    public EmployeeResponseDto findById(EmployeeRequestDto request) {
        Employee employee = employeeRepository.findById(request.getId()).get();
        EmployeeResponseDto employeeResponse = new EmployeeResponseDto();
        employeeResponse.setName(employee.getName());
        employeeResponse.setEmail(employee.getEmail());
        return employeeResponse;
    }

    public List<EmployeeResponseDto> findtByName (String name) {
        List<Employee> employeeList = employeeRepository.getByName(name);
        List<EmployeeResponseDto> employeeResponse = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeResponseDto newEmployee = new EmployeeResponseDto();
            newEmployee.setName(employee.getName());
            newEmployee.setEmail(employee.getEmail());
            employeeResponse.add(newEmployee);
        }
        return employeeResponse;
    }

    public Boolean existByEmail (String email) {
        return employeeRepository.existsByEmail(email);
    }

    public EmployeeResponseDto createEmployee (Employee employee) throws Exception {
        if (existByEmail(employee.getEmail())) {
            throw new Exception("Email already used");
        }
        try {
            Employee newEmployee = employeeRepository.save(employee);
            EmployeeResponseDto employeeResponse = new EmployeeResponseDto();
            employeeResponse.setName(newEmployee.getName());
            employeeResponse.setEmail(newEmployee.getEmail());
            return employeeResponse;
        } catch (Exception e) {
            throw e;
        }
    }

    public EmployeeResponseDto updateEmployee (long id, UpdateEmployeeRequestDto request) {
        Employee employee = employeeRepository.findById(id).get();
        employee.setId(id);
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee = employeeRepository.save(employee);

        EmployeeResponseDto newEmployee = new EmployeeResponseDto();
        newEmployee.setName(employee.getName());
        newEmployee.setEmail(employee.getEmail());
        return newEmployee;
    }

    public EmployeeResponseDto deleteEmployee (EmployeeRequestDto request) throws Exception {
        EmployeeResponseDto employee = findById(request);
        if (employee == null) {
            throw new Exception("User not found");
        }
        employeeRepository.deleteById(request.getId());
        return employee;
    }
}
