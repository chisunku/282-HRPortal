package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface salariesService extends JpaRepository<salaries, Long> {
    public List<salaries> findSalaryByEmpNo(int empNo);
}
