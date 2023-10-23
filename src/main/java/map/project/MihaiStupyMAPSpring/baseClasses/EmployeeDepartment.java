package map.project.MihaiStupyMAPSpring.baseClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class EmployeeDepartment {
    @Id
    @Column(name = "departmentID")
    private int departmentID;
    @Column(name = "maxEmployees")
    private int maxEmployees;
    @Column(name = "specialization")
    private String specialization;
    @Column(name = "leader")
    private DepartmentLeader leader;

}
