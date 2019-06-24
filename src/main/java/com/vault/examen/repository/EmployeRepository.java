package com.vault.examen.repository;

import com.vault.examen.domain.Employe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long>, EmployeRepositoryCustom {

    public List<Employe> findByDepartmentLocationId(Long locationId);
    
    public List<Employe> findByManagerId(Long managerId);
}
