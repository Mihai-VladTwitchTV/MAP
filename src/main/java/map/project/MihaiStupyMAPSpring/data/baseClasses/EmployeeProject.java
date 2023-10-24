package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "EmployeeProject")
@IdClass(EmployeeProjectId.class)///aici e diferita implementarea fata de celalalt ManyToMany(EmployeeSkill), ar trebui sa alegem una numa sa nu pierdem puncte la consistenta
public class EmployeeProject {
    public EmployeeProject(Employee employee, Project project, Assignments assignments) {
        this.employee = employee;
        this.project = project;
        this.assignments = assignments;
    }

    public EmployeeProject() {
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name = "projectID")
    private Project project;

    @OneToOne
    @JoinColumn(name = "assignmentID")
    private Assignments assignments;
}

