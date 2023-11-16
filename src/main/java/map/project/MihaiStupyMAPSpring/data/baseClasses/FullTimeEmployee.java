package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("FULL_TIME")
public class FullTimeEmployee extends Employee implements FullTime {
    public FullTimeEmployee() {
        setType("FULL_TIME");
    }

    public FullTimeEmployee(int employeeID, String firstName, String lastName, int phoneNumber, String emailAddress, Department department) {
        super(employeeID, firstName, lastName, phoneNumber, emailAddress, department);
        setType("FULL_TIME");
    }

    @Override
    public void setFullTime(boolean isFullTime) {
        this.isFullTime = isFullTime;
    }
}