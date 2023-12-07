package map.project.MihaiStupyMAPSpring.data.baseClasses;


import jakarta.persistence.*;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "Project")
public class Project {
    public Project() {
    }

    public Project(int projectID, Client client, Department department, String projectName, Date startDate, Date endDate, String status, String meetingType, Set<Assignments> assignments, Set<ProjectCosts> costs, Set<ProjectMilestones> milestones) {
        this.projectID = projectID;
        this.client = client;
        this.department = department;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.meetingType = meetingType;
        this.assignments = assignments;
        this.costs = costs;
        this.milestones = milestones;
    }

    @Getter
    @Setter
    @Id
    private int projectID;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "clientID")
    private Client client;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "departmentID")
    private Department department;

    @Getter
    @Setter
    @Column(name = "project_name", insertable = false, updatable = false)
    private String projectName;


    @Getter
    @Setter
    @Column(name = "startDate")
    private Date startDate;

    @Getter
    @Setter
    @Column(name = "endDate")
    private Date endDate;

    @Getter
    @Setter
    @Column(name = "status")
    private String status;

    @Getter
    @Setter
    @Column(name = "meetingType")
    private String meetingType;

    @Setter
    @Getter
    @OneToMany(mappedBy = "project")
    private Set<Assignments> assignments;

    @Getter
    @OneToMany(mappedBy = "project")
    private Set<ProjectCosts> costs;

    @Getter
    @OneToMany(mappedBy = "project")
    private Set<ProjectMilestones> milestones;


}

