package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserLogin extends JpaRepository<Users, Long> {
    public Users findUserByEmpId (int empId);

    @Query(value = "SELECT * FROM USER",nativeQuery = true)
    List<Users> findUsers();
}

