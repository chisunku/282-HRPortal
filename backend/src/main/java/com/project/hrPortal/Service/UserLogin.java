package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserLogin extends JpaRepository<Users, Long> {
    public Users findUserByEmpId (int empId);
}

