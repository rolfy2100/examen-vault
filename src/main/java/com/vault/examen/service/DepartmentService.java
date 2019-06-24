package com.vault.examen.service;

import com.vault.examen.domain.Department;
import com.vault.examen.domain.Employe;
import com.vault.examen.domain.Location;
import com.vault.examen.repository.DepartmentRepository;
import com.vault.examen.repository.EmployeRepository;
import com.vault.examen.repository.LocationRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Transactional
public class DepartmentService {

    private final static String PROMEDIO_LIMITE_PRIMERA_MITAD_MES = "1000";
    private final static String PROMEDIO_LIMITE_SEGUNDA_MITAD_MES = "1500";
    private final static int PRIMERA_MITAD_DEL_MES = 14;
    private final static int SEGUNDA_MITAD_DEL_MES = 15;

    private final DepartmentRepository departmentRepository;
    private final EmployeRepository employeRepository;
    private final LocationRepository locationRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeRepository employeRepository, LocationRepository locationRepository) {
        this.departmentRepository = departmentRepository;
        this.employeRepository = employeRepository;
        this.locationRepository = locationRepository;
    }

    public Department guardar(Department department) {
        department.setId(null);
        department.setManager(null);
        this.validarDepartment(department);

        BigDecimal promedioSalarioEmpleadosLocation = this.calcularPromedioSalarioPorLocation(department.getLocation().getId());
        if (this.esSalarioPromedioValidoParaPrimeraMitadDelMes(promedioSalarioEmpleadosLocation) || this.esSalarioPromedioValidoParaSegundaMitadDelMes(promedioSalarioEmpleadosLocation)) {
            Location location = locationRepository.findById(department.getLocation().getId()).get();
            department.setLocation(location);
            return departmentRepository.save(department);
        } else {
            throw new IllegalArgumentException("No es posible guardar el departamento, ya que el promedio de salario es superior al permitido para esta parte del mes");
        }
    }

    private void validarDepartment(Department department) {
        Assert.notNull(department, "El departamento no puede ser nulo");
        Assert.notNull(department.getName(), "El nombre del departamento no puede ser nulo");
        Assert.notNull(department.getLocation(), "La ubicacion no puede ser nula");
        Assert.notNull(department.getLocation().getId(), "El id de la ubicacion no puede ser nula");
        Assert.isTrue(locationRepository.existsById(department.getLocation().getId()), "La ubicacion no existe");
    }

    private BigDecimal calcularPromedioSalarioPorLocation(Long locationId) {
        List<Employe> empleadosDeLocation = employeRepository.findByDepartmentLocationId(locationId);
        if (empleadosDeLocation.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return empleadosDeLocation.stream().map(employe -> employe.getSalary())
                    .reduce(BigDecimal.ZERO, (salary, salary2) -> salary.add(salary2))
                    .divide(new BigDecimal(empleadosDeLocation.size()), RoundingMode.FLOOR);
        }
    }

    private boolean esSalarioPromedioValidoParaPrimeraMitadDelMes(BigDecimal promedioSalarioEmpleadosLocation) {
        return this.esPromedioValido(promedioSalarioEmpleadosLocation, new BigDecimal(PROMEDIO_LIMITE_PRIMERA_MITAD_MES)) && this.esDiaDelMesValido(PRIMERA_MITAD_DEL_MES);
    }

    private boolean esSalarioPromedioValidoParaSegundaMitadDelMes(BigDecimal promedioSalarioEmpleadosLocation) {
        return this.esPromedioValido(promedioSalarioEmpleadosLocation, new BigDecimal(PROMEDIO_LIMITE_SEGUNDA_MITAD_MES)) && this.esDiaDelMesValido(SEGUNDA_MITAD_DEL_MES);
    }

    private boolean esPromedioValido(BigDecimal promedioSalario, BigDecimal promedioLimite) {
        return promedioSalario.compareTo(promedioLimite) <= 0;
    }

    private boolean esDiaDelMesValido(int mitadDelMesAValidar) {
        return LocalDate.now().getDayOfMonth() >= mitadDelMesAValidar;
    }
}
