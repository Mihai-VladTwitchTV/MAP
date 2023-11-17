package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PART_TIME")
public class PartTimeEmployee extends Employee {
    @Column(name = "isPartTime")
    private boolean isPartTime;

    // Additional fields and methods specific to PartTimeEmployee can be added here

    public void setIsPartTime(boolean isPartTime) {
        this.isPartTime = isPartTime;
    }
}
