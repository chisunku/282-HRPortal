package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.deptEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptEmployeeService extends JpaRepository<deptEmployee, Long> {
    public List<deptEmployee> findDepartmentByEmpNo(int empNo);
}
