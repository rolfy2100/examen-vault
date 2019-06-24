package com.vault.examen.controller.rest;

import com.vault.examen.domain.Employe;
import com.vault.examen.service.EmployeService;
import com.vault.examen.vo.EmployeFilter;
import com.vault.examen.vo.EmployeVO;
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

    private final EmployeService employeService;

    public EmployeRestController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping("/employees/{id}")
    public EmployeVO buscarPorId(@PathVariable Long id) {
        Employe employeEntity = employeService.buscarPorId(id);
        EmployeVO employeVO = this.convertirEnVO(employeEntity);
        return employeVO;
    }

    @GetMapping("/employees")
    public List<Employe> buscarEmployees(EmployeFilter employeFilter) {
        return employeService.buscarPorFiltros(employeFilter);
    }

    @PostMapping("/employees")
    public EmployeVO guardar(@RequestBody Employe employe) {
        Employe employeEntity = employeService.guardar(employe);
        EmployeVO employeVO = this.convertirEnVO(employeEntity);
        return employeVO;
    }

    @PutMapping("/employees/{id}")
    public EmployeVO actualizar(@RequestBody Employe employe, @PathVariable Long id) {
        employe.setId(id);
        Employe employeEntity = employeService.actualizar(employe);
        EmployeVO employeVO = this.convertirEnVO(employeEntity);
        return employeVO;
    }

    @DeleteMapping("/employees/{id}")
    public void eliminar(@PathVariable Long id) {
        employeService.eliminar(id);
    }

    private EmployeVO convertirEnVO(Employe employeEntity) {
        EmployeVO employeVO = new EmployeVO(employeEntity);
        employeVO.setManager(new EmployeVO(employeEntity.getManager()));
        return employeVO;
    }
}
