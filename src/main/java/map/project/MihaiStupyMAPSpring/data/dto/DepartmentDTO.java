package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;

public class DepartmentDTO {
    private int maxEmployees;
    private String specialization;

    // Getters and setters
    public int getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(int maxEmployees) {
        this.maxEmployees = maxEmployees;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Department toDepartment() {
        Department department = new Department();
        department.setMaxEmployees(this.maxEmployees);
        department.setSpecialization(this.specialization);
        return department;
    }
}
