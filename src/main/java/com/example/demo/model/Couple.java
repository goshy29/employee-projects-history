package com.example.demo.model;

public class Couple {
    private int employeeId1;
    private int employeeId2;
    private String description;

    public Couple(int employeeId1, int employeeId2, String description) {
        this.employeeId1 = employeeId1;
        this.employeeId2 = employeeId2;
        this.description = description;
    }

    public int getEmployeeId1() {
        return employeeId1;
    }

    public void setEmployeeId1(int employeeId1) {
        this.employeeId1 = employeeId1;
    }

    public int getEmployeeId2() {
        return employeeId2;
    }

    public void setEmployeeId2(int employeeId2) {
        this.employeeId2 = employeeId2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
