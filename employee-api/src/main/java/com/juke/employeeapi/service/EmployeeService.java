package com.juke.employeeapi.service;

import com.juke.employeeapi.dto.EmployeeRequest;
import com.juke.employeeapi.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    
    // Ambil semua data karyawan
    List<EmployeeResponse> getAllEmployees();
    
    // Cari karyawan pakai ID
    EmployeeResponse getEmployeeById(Long id);
    
    // Tambah karyawan baru
    EmployeeResponse createEmployee(EmployeeRequest request);
    
    // Update data karyawan
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);
    
    // Hapus karyawan
    void deleteEmployee(Long id);
}