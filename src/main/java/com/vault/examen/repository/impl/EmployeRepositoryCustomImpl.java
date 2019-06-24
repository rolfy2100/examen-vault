package com.vault.examen.repository.impl;

import com.vault.examen.domain.Employe;
import com.vault.examen.vo.EmployeFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.vault.examen.repository.EmployeRepositoryCustom;

@Repository
public class EmployeRepositoryCustomImpl implements EmployeRepositoryCustom {

    private final EntityManager entityManager;
    private static final String QUERY_EMPLOYE = "FROM Employe emp WHERE 0 = 0";

    public EmployeRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employe> buscarPorLastNameAndJobAndManager(EmployeFilter employFilter) {
        String query = QUERY_EMPLOYE.concat(this.agregarFiltroPorJob(employFilter.getJobId()))
                .concat(this.agregarFiltroPorLastName(employFilter.getLastName()))
                .concat(this.agregarFiltroPorManager(employFilter.getManagerId()));
        TypedQuery<Employe> typedQuery = entityManager.createQuery(query, Employe.class);
        this.agregarPaginado(employFilter.getLimit(), employFilter.getOffset(), typedQuery);
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

    private void agregarPaginado(Integer limit, Integer offset, TypedQuery<Employe> typedQuery) {
        if (offset != null && limit != null) {
            typedQuery.setFirstResult(offset).setMaxResults(limit);
        }
    }

}
