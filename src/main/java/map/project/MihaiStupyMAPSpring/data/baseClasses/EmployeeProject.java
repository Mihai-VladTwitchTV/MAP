package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "EmployeeProject")
@IdClass(EmployeeProjectId.class)
public class EmployeeProject {
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

