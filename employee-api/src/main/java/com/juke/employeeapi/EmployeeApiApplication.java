package com.juke.employeeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Ini file utama untuk jalankan aplikasi
@SpringBootApplication
public class EmployeeApiApplication {
    
    // Method main - titik awal program jalan
    public static void main(String[] args) {
        // Jalankan aplikasi Spring Boot
        SpringApplication.run(EmployeeApiApplication.class, args);
    }
}