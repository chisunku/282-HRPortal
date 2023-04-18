package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.Departments;
import com.project.hrPortal.Entity.DeptManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService extends JpaRepository<Departments, Long> {
    public Departments findDepartmentByDeptNo(String deptNo);
}
