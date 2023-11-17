package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("FULL_TIME")
public class FullTimeEmployee extends Employee {
    @Column(name = "isFullTime")
    private boolean isFullTime;

    // Additional fields and methods specific to FullTimeEmployee can be added here

    public void setIsFullTime(boolean isFullTime) {
        this.isFullTime = isFullTime;
    }
}
