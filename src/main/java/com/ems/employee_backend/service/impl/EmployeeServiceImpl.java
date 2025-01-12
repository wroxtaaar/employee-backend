package com.ems.employee_backend.service.impl;

import com.ems.employee_backend.dto.EmployeeDTO;
import com.ems.employee_backend.entity.Employee;
import com.ems.employee_backend.exception.ResourceNotFoundException;
import com.ems.employee_backend.mapper.EmployeeMapper;
import com.ems.employee_backend.repository.EmployeeRepository;
import com.ems.employee_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with the given id: " + id));
        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
//        return allEmployees.stream().map((e) -> EmployeeMapper.mapToEmployeeDTO(e)).collect(Collectors.toList());
        return allEmployees.stream().map(EmployeeMapper::mapToEmployeeDTO).collect(Collectors.toList());

    }

    @Override
    public EmployeeDTO updateEmployeeById(EmployeeDTO updatedEmployeeDTO, Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with given id: " + id));

        employee.setFirstName(updatedEmployeeDTO.getFirstName());
        employee.setLastName(updatedEmployeeDTO.getLastName());
        employee.setEmail(updatedEmployeeDTO.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with given id: " + id)
        );

        employeeRepository.deleteById(id);

    }


}
