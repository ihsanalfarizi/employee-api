package com.juke.employeeapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data                   // Lombok: auto bikin getter, setter, toString
@Builder                // Bikin builder pattern (gampang bikin object)
@NoArgsConstructor      // Constructor kosong
@AllArgsConstructor     // Constructor lengkap
@Schema(description = "Response data karyawan")  // Dokumentasi Swagger
public class EmployeeResponse {
    
    @Schema(description = "ID karyawan", example = "1")
    private Long id;  // ID unik tiap karyawan
    
    @Schema(description = "Nama lengkap karyawan", example = "John Doe")
    private String name;  // Nama karyawan
    
    @Schema(description = "Email karyawan", example = "john.doe@juke.com")
    private String email;  // Email karyawan
    
    @Schema(description = "Jabatan karyawan", example = "Software Engineer")
    private String position;  // Posisi/jabatan
    
    @Schema(description = "Gaji karyawan", example = "15000000.00")
    private Double salary;  // Gaji 
    
    @Schema(description = "Waktu data dibuat", example = "2025-10-28T10:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")  // Format tanggal pas dijadiin JSON
    private LocalDateTime createdAt;  // Kapan data ini dibuat
}