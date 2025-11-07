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
import java.time.LocalDateTime;  // ⭐ TAMBAH
import java.util.HashMap;        // ⭐ TAMBAH
import java.util.Map;            // ⭐ TAMBAH
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Juke Employee Management", description = "Employee Management REST API")
public class EmployeeController {

    private final EmployeeService employeeService;

    // ✅ GET ALL - dengan pesan berhasil
    @GetMapping
    @Operation(summary = "Menampilkan semua karyawan")
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Successfully retrieved all employees");
        response.put("data", employees);
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }

    // ✅ GET BY ID - dengan pesan berhasil
    @GetMapping("/{id}")
    @Operation(summary = "Menampilkan detail 1 karyawan")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Successfully retrieved employee");
        response.put("data", employee);
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }

    // ✅ CREATE - dengan pesan berhasil
    @PostMapping
    @Operation(summary = "Menambahkan data karyawan baru")
    public ResponseEntity<Map<String, Object>> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse newEmployee = employeeService.createEmployee(request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Employee created successfully");
        response.put("data", newEmployee);
        response.put("timestamp", LocalDateTime.now());
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ✅ UPDATE - dengan pesan berhasil
    @PutMapping("/{id}")
    @Operation(summary = "Mengubah data karyawan")
    public ResponseEntity<Map<String, Object>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse updatedEmployee = employeeService.updateEmployee(id, request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Employee updated successfully");
        response.put("data", updatedEmployee);
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }

    // ✅ DELETE - dengan pesan berhasil
    @DeleteMapping("/{id}")
    @Operation(summary = "Menghapus data karyawan")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Employee deleted successfully");
        response.put("data", null);  // Gak ada data yang dikembalikan
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }
}
