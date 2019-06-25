package com.vault.examen.repository;

import com.vault.examen.domain.Employee;
import com.vault.examen.vo.EmployeeFilter;
import java.util.List;

public interface EmployeeRepositoryCustom {

    public List<Employee> buscarPorLastNameAndJobAndManager(EmployeeFilter employeeFilter);
}
