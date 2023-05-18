package com.geekster.EmployeeAddress.repository;
import com.geekster.EmployeeAddress.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}