package com.juke.employeeapi.controller;

import com.juke.employeeapi.dto.EmployeeRequest;
import com.juke.employeeapi.dto.EmployeeResponse;
import com.juke.employeeapi.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Ini controller untuk handle semua request yang berhubungan dengan data karyawan
@RestController                           
@RequestMapping("/api/employees")         // Base URL nya di /api/employees
@RequiredArgsConstructor                  
@Tag(name = "Juke Employee Management", description = "Employee Management REST API")
public class EmployeeController {

    private final EmployeeService employeeService;  // Service buat handle logic bisnis

    // Endpoint buat ambil semua data karyawan
    @GetMapping                           
    @Operation(summary = "Menampilkan semua karyawan")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Endpoint buat ambil data karyawan berdasarkan ID
    @GetMapping("/{id}")                  
    @Operation(summary = "Menampilkan detail 1 karyawan")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        // @PathVariable ngambil id dari URL
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // Endpoint buat nambahin karyawan baru
    @PostMapping                          
    @Operation(summary = "Menambahkan data karyawan baru")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        // @RequestBody buat nerima data dari body request
        // @Valid buat validasi input (misal email harus format email, nama gak boleh kosong, dll)
        return new ResponseEntity<>(employeeService.createEmployee(request), HttpStatus.CREATED);
    }

    // Endpoint buat update data karyawan yang udah ada
    @PutMapping("/{id}")                  
    @Operation(summary = "Mengubah data karyawan")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,        
            @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    // Endpoint buat hapus data karyawan
    @DeleteMapping("/{id}")               
    @Operation(summary = "Menghapus data karyawan")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}