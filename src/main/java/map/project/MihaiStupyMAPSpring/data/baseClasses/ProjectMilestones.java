package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Table(name = "ProjectMilestones")
public class ProjectMilestones {
    public ProjectMilestones() {
    }

    public ProjectMilestones(int milestoneID, Project project, String milestoneName, String description) {
        this.milestoneID = milestoneID;
        this.project = project;
        this.milestoneName = milestoneName;
        this.description = description;
    }

    @Getter
    @Setter
    @Id
    private int milestoneID;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "projectID")
    private Project project;

    @Getter
    @Setter
    @Column(name = "milestoneName")
    private String milestoneName;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;
}
