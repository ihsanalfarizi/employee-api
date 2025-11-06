package com.juke.employeeapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID unik karyawan

    @Column(nullable = false)
    private String name; // Nama karyawan

    @Column(nullable = false, unique = true)
    private String email; // Email (harus unik)

    @Column(nullable = false)
    private String position; // Jabatan

    @Column(nullable = false)
    private Double salary; // Gaji

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // Waktu data dibuat
}