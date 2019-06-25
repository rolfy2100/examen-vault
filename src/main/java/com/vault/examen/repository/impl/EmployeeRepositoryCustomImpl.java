package com.vault.examen.repository.impl;

import com.vault.examen.domain.Employee;
import com.vault.examen.vo.EmployeeFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.vault.examen.repository.EmployeeRepositoryCustom;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    private final EntityManager entityManager;
    private static final String QUERY_EMPLOYE = "FROM Employee emp WHERE 0 = 0";

    public EmployeeRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> buscarPorLastNameAndJobAndManager(EmployeeFilter employeeFilter) {
        String query = QUERY_EMPLOYE.concat(this.agregarFiltroPorJob(employeeFilter.getJobId()))
                .concat(this.agregarFiltroPorLastName(employeeFilter.getLastName()))
                .concat(this.agregarFiltroPorManager(employeeFilter.getManagerId()));
        TypedQuery<Employee> typedQuery = entityManager.createQuery(query, Employee.class);
        this.agregarPaginado(employeeFilter.getLimit(), employeeFilter.getOffset(), typedQuery);
        return typedQuery.getResultList();
    }

    private String agregarFiltroPorJob(String jobId) {
        return jobId != null ? " and emp.job.id = '" + jobId + "'" : "";
    }

    private String agregarFiltroPorLastName(String lastName) {
        return lastName != null ? " and emp.lastName = '" + lastName + "'" : "";
    }

    private String agregarFiltroPorManager(Long managerId) {
        return managerId != null ? " and emp.manager.id = " + managerId : "";
    }

    private void agregarPaginado(Integer limit, Integer offset, TypedQuery<Employee> typedQuery) {
        if (offset != null && limit != null) {
            typedQuery.setFirstResult(offset).setMaxResults(limit);
        }
    }

}
