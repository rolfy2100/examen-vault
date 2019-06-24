package com.vault.examen.service;

import com.vault.examen.domain.Department;
import com.vault.examen.domain.Employe;
import com.vault.examen.domain.Job;
import com.vault.examen.repository.DepartmentRepository;
import com.vault.examen.repository.EmployeRepository;
import com.vault.examen.repository.JobRepository;
import com.vault.examen.vo.EmployeFilter;
import java.util.Comparator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Transactional
public class EmployeService {

    private final EmployeRepository employeRepository;
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;

    public EmployeService(EmployeRepository employeRepository, DepartmentRepository departmentRepository, JobRepository jobRepository) {
        this.employeRepository = employeRepository;
        this.departmentRepository = departmentRepository;
        this.jobRepository = jobRepository;
    }

    public List<Employe> buscarPorFiltros(EmployeFilter employeFilter) {
        List<Employe> empleados;
        if (employeFilter.isEmpty()) {
            empleados = employeRepository.findAll();
        } else {
            empleados = employeRepository.buscarPorLastNameAndJobAndManager(employeFilter);
        }
        empleados.sort(Comparator.comparing(Employe::getHireDate));
        return empleados;
    }

    public Employe buscarPorId(Long id) {
        return employeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El empleado no existe"));
    }

    public Employe guardar(Employe employe) {
        employe.setId(null);
        this.validarEmploye(employe);

        employe.setDepartment(this.getDepartment(employe.getDepartment().getId()));
        employe.setJob(this.getJob(employe.getJob().getId()));
        employe.setManager(this.getEmploye(employe.getManager().getId()));
        return employeRepository.save(employe);
    }

    public Employe actualizar(Employe employe) {
        Employe employePersist = employeRepository.findById(employe.getId()).orElseThrow(() -> new IllegalArgumentException("El empleado a actualizar no existe"));
        this.validarDatosBasicosEmploye(employe);
        employePersist.setFirstName(employe.getFirstName());
        employePersist.setLastName(employe.getLastName());
        employePersist.setPhoneNumber(employe.getPhoneNumber());
        employePersist.setEmail(employe.getEmail());
        employePersist.setHireDate(employe.getHireDate());
        employePersist.setSalary(employe.getSalary());
        employePersist.setCommissionPct(employe.getCommissionPct());
        return employePersist;
    }

    public void eliminar(Long idEmpleadoAEliminar) {
        Assert.notNull(idEmpleadoAEliminar, "El id del empleado es obligatorio");
        List<Employe> empleadosACargoDeEmpleadoAEliminar = employeRepository.findByManagerId(idEmpleadoAEliminar);
        empleadosACargoDeEmpleadoAEliminar.forEach(employe -> employe.setManager(null));
        List<Department> departamentoACargoDeEmpleadoAEliminar = departmentRepository.findByManagerId(idEmpleadoAEliminar);
        departamentoACargoDeEmpleadoAEliminar.forEach(department -> department.setManager(null));
        employeRepository.deleteById(idEmpleadoAEliminar);
    }

    private void validarEmploye(Employe employe) {
        this.validarDatosBasicosEmploye(employe);
        Assert.notNull(employe.getDepartment(), "El departamento es obligatorio");
        Assert.notNull(employe.getJob(), "El trabajo es obligatorio");
        Assert.notNull(employe.getManager(), "El manager es obligatorio");
        Assert.notNull(employe.getDepartment().getId(), "El id del departamento es obligatorio");
        Assert.notNull(employe.getJob().getId(), "El id del trabajo es obligatorio");
        Assert.notNull(employe.getManager().getId(), "El id del manager es obligatorio");
    }

    private void validarDatosBasicosEmploye(Employe employe) {
        Assert.hasText(employe.getFirstName(), "El nombre es obligatorio");
        Assert.hasText(employe.getLastName(), "El apellido es obligatorio");
        Assert.notNull(employe.getHireDate(), "La fecha de contratacion es obligatorio");
        Assert.notNull(employe.getSalary(), "El salario es obligatorio");
    }

    private Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El departamento no existe"));
    }

    private Job getJob(String id) {
        return jobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El trabajo no existe"));
    }

    private Employe getEmploye(Long id) {
        return employeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El manager no existe"));
    }
}
