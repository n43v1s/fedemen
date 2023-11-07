package com.example.fedmen.controllers;

import com.example.fedmen.domains.Employee;
import com.example.fedmen.domains.dto.EmployeeRequestDto;
import com.example.fedmen.domains.dto.EmployeeResponseDto;
import com.example.fedmen.domains.dto.update.UpdateEmployeeRequestDto;
import com.example.fedmen.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping("/")
    public List<EmployeeResponseDto> getAll () {
        return employeeService.findAll();
    }

    @PostMapping("/getById") //"/{id}"
    public EmployeeResponseDto getById (@RequestBody EmployeeRequestDto request) {
        return employeeService.findById(request);
    }

    @PostMapping("/getByName") //"/{name}"
    public List<EmployeeResponseDto> getByName (@RequestBody String name) {
        return employeeService.findtByName(name);
    }

    @PostMapping("/create")
    public EmployeeResponseDto getByName (@RequestBody Employee employee) throws Exception {
        return employeeService.createEmployee(employee);
    }

    @PostMapping("/update") //"/{id}"
    public EmployeeResponseDto update (@RequestBody long id, UpdateEmployeeRequestDto request) throws Exception {
        return employeeService.updateEmployee(id, request);
    }
    @PostMapping("/delete") //"/{id}"
    public EmployeeResponseDto update (@RequestBody EmployeeRequestDto request) throws Exception {
        return employeeService.deleteEmployee(request);
    }
}
