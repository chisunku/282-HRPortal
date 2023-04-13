package com.project.hrPortal.Service;

import com.project.hrPortal.Entity.Titles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TitlesService extends JpaRepository<Titles, Long> {
}
