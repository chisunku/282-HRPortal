package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.DeptManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartManagerService extends JpaRepository<DeptManager, Long> {
    public List<DeptManager> findManagersByDeptNo(String deptNo);
}
