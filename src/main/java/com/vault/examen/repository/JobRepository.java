package com.vault.examen.repository;

import com.vault.examen.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, String>{
    
}
