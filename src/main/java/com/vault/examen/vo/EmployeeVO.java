package com.vault.examen.vo;

import com.vault.examen.domain.Employee;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeVO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private BigDecimal salary;
    private BigDecimal comissionPct;
    private EmployeeVO manager;

    public EmployeeVO() {
    }

    public EmployeeVO(Employee employeEntity) {
        this.id = employeEntity.getId();
        this.firstName = employeEntity.getFirstName();
        this.lastName = employeEntity.getLastName();
        this.email = employeEntity.getEmail();
        this.phoneNumber = employeEntity.getPhoneNumber();
        this.hireDate = employeEntity.getHireDate();
        this.salary = employeEntity.getSalary();
        this.comissionPct = employeEntity.getCommissionPct();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getComissionPct() {
        return comissionPct;
    }

    public void setComissionPct(BigDecimal comissionPct) {
        this.comissionPct = comissionPct;
    }

    public EmployeeVO getManager() {
        return manager;
    }

    public void setManager(EmployeeVO manager) {
        this.manager = manager;
    }
}
