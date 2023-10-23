package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class EmployeeProjectId implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name = "projectID")
    private Project project;
}
