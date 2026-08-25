package com.example.backend.mapper;

import com.example.backend.api.model.EmployeeRequest;
import com.example.backend.api.model.EmployeeResponse;
import com.example.backend.entity.Employee;

public final class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static Employee toEntity(EmployeeRequest request) {
        Employee employee = new Employee();
        applyRequest(employee, request);
        return employee;
    }

    public static void applyRequest(Employee employee, EmployeeRequest request) {
        employee.setName(request.getName());
        employee.setSalary(request.getSalary());
        employee.setDepartment(request.getDepartment());
        employee.setAge(request.getAge());
    }

    public static EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse()
                .id(employee.getId())
                .name(employee.getName())
                .salary(employee.getSalary())
                .department(employee.getDepartment())
                .age(employee.getAge());
    }
}
