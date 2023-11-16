package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("LEADER")
public class LeadEmployee extends Employee {
    @Column(name = "isLeader")
    private boolean isLeader;

    // Additional fields and methods specific to LeaderEmployee can be added here

    public void setIsLeader(boolean isLeader) {
        this.isLeader = isLeader;
    }
}
