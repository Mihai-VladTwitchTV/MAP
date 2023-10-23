package map.project.MihaiStupyMAPSpring.data.baseClasses;


import jakarta.persistence.*;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "Project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectID;

    @ManyToOne
    @JoinColumn(name = "clientID")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "departmentID")
    private Department department;

    private String projectName;
    private Date startDate;
    private Date endDate;
    private String status;
    private String meetingType;

    @OneToMany(mappedBy = "project")
    private Set<EmployeeProject> assignments;

    @OneToMany(mappedBy = "project")
    private Set<ProjectCosts> costs;

    @OneToMany(mappedBy = "project")
    private Set<ProjectMilestones> milestones;
}

