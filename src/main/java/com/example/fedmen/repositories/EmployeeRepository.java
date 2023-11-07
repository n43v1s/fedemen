package com.example.fedmen.repositories;

import com.example.fedmen.domains.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM tb_employee WHERE name = :name", nativeQuery = true)
    public List<Employee> getByName(String name);

    // public Employee findByName(String name);

    public Boolean existsByEmail(String email);
}
