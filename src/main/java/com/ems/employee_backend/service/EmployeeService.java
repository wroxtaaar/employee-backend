package com.ems.employee_backend.service;

import com.ems.employee_backend.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long id);

    void deleteEmployeeById(Long id);
}
