package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.Employee;
import com.project.hrPortal.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService extends JpaRepository<Employee, Long> {
    public Employee findEmployeeByEmpNo (int empNo);
}

