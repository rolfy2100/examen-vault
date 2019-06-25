package com.vault.examen.service;

import com.vault.examen.domain.Department;
import com.vault.examen.domain.Employee;
import com.vault.examen.domain.Job;
import com.vault.examen.repository.DepartmentRepository;
import com.vault.examen.repository.JobRepository;
import com.vault.examen.vo.EmployeeFilter;
import java.util.Comparator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.vault.examen.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.jobRepository = jobRepository;
    }

    public List<Employee> buscarPorFiltros(EmployeeFilter employeeFilter) {
        List<Employee> empleados;
        if (employeeFilter.isEmpty()) {
            empleados = employeeRepository.findAll();
        } else {
            empleados = employeeRepository.buscarPorLastNameAndJobAndManager(employeeFilter);
        }
        empleados.sort(Comparator.comparing(Employee::getHireDate));
        return empleados;
    }

    public Employee buscarPorId(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El empleado no existe"));
    }

    public Employee guardar(Employee employee) {
        employee.setId(null);
        this.validarEmploye(employee);

        employee.setDepartment(this.getDepartment(employee.getDepartment().getId()));
        employee.setJob(this.getJob(employee.getJob().getId()));
        if (employee.getManager() != null && employee.getManager().getId() != null) {
            employee.setManager(this.getEmployee(employee.getManager().getId()));
        }
        return employeeRepository.save(employee);
    }

    public Employee actualizar(Employee employee) {
        Employee employeePersist = employeeRepository.findById(employee.getId()).orElseThrow(() -> new IllegalArgumentException("El empleado a actualizar no existe"));
        this.validarDatosBasicosEmploye(employee);
        employeePersist.setFirstName(employee.getFirstName());
        employeePersist.setLastName(employee.getLastName());
        employeePersist.setPhoneNumber(employee.getPhoneNumber());
        employeePersist.setEmail(employee.getEmail());
        employeePersist.setHireDate(employee.getHireDate());
        employeePersist.setSalary(employee.getSalary());
        employeePersist.setCommissionPct(employee.getCommissionPct());
        return employeePersist;
    }

    public void eliminar(Long idEmpleadoAEliminar) {
        Assert.notNull(idEmpleadoAEliminar, "El id del empleado es obligatorio");
        Assert.isTrue(employeeRepository.existsById(idEmpleadoAEliminar), "El empleado a eliminar no existe");
        List<Employee> empleadosACargoDeEmpleadoAEliminar = employeeRepository.findByManagerId(idEmpleadoAEliminar);
        empleadosACargoDeEmpleadoAEliminar.forEach(employee -> employee.setManager(null));
        List<Department> departamentoACargoDeEmpleadoAEliminar = departmentRepository.findByManagerId(idEmpleadoAEliminar);
        departamentoACargoDeEmpleadoAEliminar.forEach(department -> department.setManager(null));
        employeeRepository.deleteById(idEmpleadoAEliminar);
    }

    private void validarEmploye(Employee employee) {
        this.validarDatosBasicosEmploye(employee);
        Assert.notNull(employee.getDepartment(), "El departamento es obligatorio");
        Assert.notNull(employee.getJob(), "El trabajo es obligatorio");
        Assert.notNull(employee.getDepartment().getId(), "El id del departamento es obligatorio");
        Assert.notNull(employee.getJob().getId(), "El id del trabajo es obligatorio");
    }

    private void validarDatosBasicosEmploye(Employee employee) {
        Assert.hasText(employee.getFirstName(), "El nombre es obligatorio");
        Assert.hasText(employee.getLastName(), "El apellido es obligatorio");
        Assert.notNull(employee.getHireDate(), "La fecha de contratacion es obligatorio");
        Assert.notNull(employee.getSalary(), "El salario es obligatorio");
    }

    private Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El departamento no existe"));
    }

    private Job getJob(String id) {
        return jobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El trabajo no existe"));
    }

    private Employee getEmployee(Long id) {
        return employeeRepository.getOne(id);
    }
}
