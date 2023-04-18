package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.Employees;
import com.project.hrPortal.Entity.Titles;
import com.project.hrPortal.Entity.TitlesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TitlesService extends JpaRepository<Titles, Long> {
    public List<Titles> findTitlesByEmployee(Employees emp);
    public Titles findTitleByEmpNo (int empNo);
}


