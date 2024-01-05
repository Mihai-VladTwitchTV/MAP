package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;




@Entity
@Data
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

//    @Override
//    public int hashCode() {
//        return Objects.hash(milestoneID);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        ProjectMilestones other = (ProjectMilestones) obj;
//        return milestoneID == other.milestoneID;
//    }

}
