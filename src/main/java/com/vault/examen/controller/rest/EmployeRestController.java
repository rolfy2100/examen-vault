package com.vault.examen.controller.rest;

import com.vault.examen.domain.Employee;
import com.vault.examen.service.EmployeeService;
import com.vault.examen.vo.EmployeeFilter;
import com.vault.examen.vo.EmployeeVO;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeRestController {

    private final EmployeeService employeeService;

    public EmployeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{id}")
    public EmployeeVO buscarPorId(@PathVariable Long id) {
        Employee employeeEntity = employeeService.buscarPorId(id);
        EmployeeVO employeeVO = this.convertirEnVO(employeeEntity);
        return employeeVO;
    }

    @GetMapping("/employees")
    public List<Employee> buscarEmployees(EmployeeFilter employeeFilter) {
        return employeeService.buscarPorFiltros(employeeFilter);
    }

    @PostMapping("/employees")
    public EmployeeVO guardar(@RequestBody Employee employee) {
        Employee employeeEntity = employeeService.guardar(employee);
        EmployeeVO employeeVO = this.convertirEnVO(employeeEntity);
        return employeeVO;
    }

    @PutMapping("/employees/{id}")
    public EmployeeVO actualizar(@RequestBody Employee employee, @PathVariable Long id) {
        employee.setId(id);
        Employee employeeEntity = employeeService.actualizar(employee);
        EmployeeVO employeeVO = this.convertirEnVO(employeeEntity);
        return employeeVO;
    }

    @DeleteMapping("/employees/{id}")
    public void eliminar(@PathVariable Long id) {
        employeeService.eliminar(id);
    }

    private EmployeeVO convertirEnVO(Employee employeeEntity) {
        EmployeeVO employeeVO = new EmployeeVO(employeeEntity);
        if (employeeEntity.getManager() != null) {
            employeeVO.setManager(new EmployeeVO(employeeEntity.getManager()));
        }
        return employeeVO;
    }
}
