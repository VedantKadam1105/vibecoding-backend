package com.example.backend.service;

import com.example.backend.api.model.EmployeeRequest;
import com.example.backend.api.model.EmployeeResponse;
import com.example.backend.entity.Employee;
import com.example.backend.exception.EmployeeNotFoundException;
import com.example.backend.mapper.EmployeeMapper;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = EmployeeMapper.toEntity(request);
        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        return EmployeeMapper.toResponse(findEmployeeOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = findEmployeeOrThrow(id);
        EmployeeMapper.applyRequest(employee, request);
        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

    private Employee findEmployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
