package map.project.MihaiStupyMAPSpring.data.baseClasses;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.Set;


@Entity
@Data
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
    @JoinColumn(name = "clientid") // adjust the column name as necessary
    @JsonBackReference // This annotation handles the back part of the reference
    private Client client;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "departmentID")
    private Department department;

    @Getter
    @Setter
    @Column(name = "project_name")
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
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)//fetch = FetchType.EAGER) //orphanRemoval = true)
    private Set<Assignments> assignments;

    @Getter
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)//fetch = FetchType.EAGER) //orphanRemoval = true)
    private Set<ProjectCosts> costs;

    @Getter
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)//fetch = FetchType.EAGER) //orphanRemoval = true)
    private Set<ProjectMilestones> milestones;

    public void addAssignment(Assignments assignment) {
        // Set the project reference in the assignment
        assignment.setProject(this);

        // Add the assignment to the project's collection
        this.assignments.add(assignment);
    }


//    @Override
//    public int hashCode() {
//        return Objects.hash(projectID);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Project other = (Project) obj;
//        return projectID == other.projectID;
//    }


}

