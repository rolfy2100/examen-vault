package com.vault.examen.repository;

import com.vault.examen.domain.Employe;
import com.vault.examen.vo.EmployeFilter;
import java.util.List;

public interface EmployeRepositoryCustom {

    public List<Employe> buscarPorLastNameAndJobAndManager(EmployeFilter employFilter);
}
