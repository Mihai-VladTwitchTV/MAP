package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "ProjectMilestones")
public class ProjectMilestones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int milestoneID;

    @ManyToOne
    @JoinColumn(name = "projectID")
    private Project project;

    private String milestoneName;
    private String description;
}
