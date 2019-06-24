package com.vault.examen.vo;

public class EmployeFilter {

    private String jobId;
    private Long managerId;
    private String lastName;
    private Integer limit;
    private Integer offset;

    public boolean isEmpty() {
        return this.jobId == null && this.managerId == null && this.lastName == null && (this.limit == null || this.offset == null);
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
