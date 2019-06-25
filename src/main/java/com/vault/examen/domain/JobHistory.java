package com.vault.examen.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "job_history")
public class JobHistory {

    @EmbeddedId
    @JsonUnwrapped
    private PrimaryKey id;
    @ManyToOne
    private Job job;
    @ManyToOne
    private Department department;

    public JobHistory() {
    }

    public LocalDate getStartDate() {
        return this.getId().getStartDate();
    }

    public void setStartDate(LocalDate startDate) {
        this.getId().setStartDate(startDate);
    }

    public LocalDate getEndDate() {
        return this.getId().getEndDate();
    }

    public void setEndDate(LocalDate endDate) {
        this.getId().setEndDate(endDate);
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Embeddable
    public static class PrimaryKey implements Serializable {

        private Long employeeId;
        private LocalDate startDate;
        private LocalDate endDate;

        public PrimaryKey() {
        }

        public Long getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Long employeeId) {
            this.employeeId = employeeId;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }
    }

    public PrimaryKey getId() {
        return id;
    }

    public void setId(PrimaryKey id) {
        this.id = id;
    }
}
