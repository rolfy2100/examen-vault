package com.vault.examen.repository;

import com.vault.examen.domain.Department;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public List<Department> findByManagerId(Long idManager);
}
