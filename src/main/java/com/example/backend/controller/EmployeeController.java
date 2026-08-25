package com.example.backend.controller;

import com.example.backend.api.EmployeeApi;
import com.example.backend.api.model.EmployeeRequest;
import com.example.backend.api.model.EmployeeResponse;
import com.example.backend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<EmployeeResponse> createEmployee(EmployeeRequest employeeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeRequest));
    }

    @Override
    public ResponseEntity<EmployeeResponse> getEmployeeById(Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Override
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @Override
    public ResponseEntity<EmployeeResponse> updateEmployee(Long id, EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequest));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
