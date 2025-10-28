package com.juke.employeeapi.service.impl;

import com.juke.employeeapi.dto.EmployeeRequest;
import com.juke.employeeapi.dto.EmployeeResponse;
import com.juke.employeeapi.model.Employee;
import com.juke.employeeapi.exception.ResourceNotFoundException;
import com.juke.employeeapi.repository.EmployeeRepository;
import com.juke.employeeapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j                      // Lombok: auto bikin logger buat catat aktivitas
@Service                    // Tandain ini adalah Service layer (business logic)
@RequiredArgsConstructor    // Auto bikin constructor buat inject dependency
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;  // Repository buat akses database
    
    // Method 1: Ambil semua karyawan
    @Override
    public List<EmployeeResponse> getAllEmployees() {
        log.info("Getting all employees");  // Log aktivitas
        
        return employeeRepository.findAll().stream()  // Ambil semua data dari DB
                .map(this::mapToResponse)             // Convert Employee jadi EmployeeResponse
                .collect(Collectors.toList());        // Kumpulin jadi List
    }
    
    // Method 2: Ambil karyawan berdasarkan ID
    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        log.info("Getting employee with id: {}", id);
        
        Employee employee = employeeRepository.findById(id)  // Cari di DB
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
                // Kalo gak ketemu, lempar exception
        
        return mapToResponse(employee);  // Convert jadi response
    }
    
    // Method 3: Tambah karyawan baru
    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        log.info("Creating new employee: {}", request.getEmail());
        
        // Bikin object Employee dari request
        Employee employee = Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .position(request.getPosition())
                .salary(request.getSalary())
                .build();
        
        Employee savedEmployee = employeeRepository.save(employee);  // Simpan ke DB
        return mapToResponse(savedEmployee);  // Return data yang udah disimpan
    }
    
    // Method 4: Update data karyawan
    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        log.info("Updating employee with id: {}", id);
        
        // Cari dulu datanya ada atau gak
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        // Update fieldnya satu-satu
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());
        
        Employee updatedEmployee = employeeRepository.save(employee);  // Save perubahan
        return mapToResponse(updatedEmployee);
    }
    
    // Method 5: Hapus karyawan
    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        
        // Cek dulu datanya ada atau gak
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        
        employeeRepository.deleteById(id);  // Hapus dari DB
    }
    
    // Helper method: Convert Employee entity jadi EmployeeResponse DTO
    private EmployeeResponse mapToResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .createdAt(employee.getCreatedAt())
                .build();
    }
}