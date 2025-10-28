package com.juke.employeeapi.repository;

import com.juke.employeeapi.model.Employee;  
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Tandain ini adalah Repository (layer akses database)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Kosong tapi udah punya banyak method bawaan dari JpaRepository
}