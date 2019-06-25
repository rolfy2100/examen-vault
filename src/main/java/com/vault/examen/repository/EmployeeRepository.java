package com.vault.examen.repository;

import com.vault.examen.domain.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {

    public List<Employee> findByDepartmentLocationId(Long locationId);

    public List<Employee> findByManagerId(Long managerId);
}
