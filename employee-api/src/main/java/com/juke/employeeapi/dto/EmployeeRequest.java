package com.juke.employeeapi.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// Annotation Lombok buat otomatis generate code
@Data                   // Auto bikin getter, setter, toString, equals, hashCode
@Builder                // Auto bikin builder pattern (biar gampang bikin object)
@NoArgsConstructor      // Auto bikin constructor tanpa parameter
@AllArgsConstructor     // Auto bikin constructor dengan semua parameter
public class EmployeeRequest {
    
    // Field NAMA dengan validasi
    @NotBlank(message = "Name is required")  // Gak boleh kosong atau cuma spasi
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")  // Minimal 2 huruf, maksimal 100
    private String name;
    
    // Field EMAIL dengan validasi
    @NotBlank(message = "Email is required")  
    @Email(message = "Email should be valid")  // Harus format email yang bener (ada @, ada domain)
    @Size(max = 255, message = "Email must not exceed 255 characters")  
    private String email;
    
    // Field POSISI/JABATAN dengan validasi
    @NotBlank(message = "Position is required")  
    @Size(min = 2, max = 100, message = "Position must be between 2 and 100 characters")  
    private String position;
    
 // Field GAJI dengan validasi
    @NotNull(message = "Salary is required")  // Wajib diisi
    @Min(value = 1, message = "Salary must be greater than 0")  // Minimal 1
    @Max(value = 99999999, message = "Salary must not exceed 99,999,999")  // Maksimal 99 juta
    private Double salary;  // Double sesuai requirement
}