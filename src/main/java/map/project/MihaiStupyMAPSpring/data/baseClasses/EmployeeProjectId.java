package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class EmployeeProjectId implements Serializable {
    public EmployeeProjectId() {
    }

    public EmployeeProjectId(Employee employee, Project project) {
        this.employee = employee;
        this.project = project;
    }

    @Setter
    @Getter
    @Id
    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @Setter
    @Getter
    @Id
    @ManyToOne
    @JoinColumn(name = "projectID")
    private Project project;
}
