package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService extends JpaRepository<Employees, Long> {
    public Employees findEmployeeByEmpNo (int empNo);

//    @Query("select * from employees e, titles t where e.emp_no = t.emp_no")
//    List<Object[]> getTitles();
}

