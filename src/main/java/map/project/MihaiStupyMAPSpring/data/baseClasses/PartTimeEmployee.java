package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("PART_TIME")
public class PartTimeEmployee extends Employee implements PartTime {
    public PartTimeEmployee() {
        setType("PART_TIME");
    }

    public PartTimeEmployee(int employeeID, String firstName, String lastName, int phoneNumber, String emailAddress, Department department) {
        super(employeeID, firstName, lastName, phoneNumber, emailAddress, department);
        setType("PART_TIME");
    }

    @Override
    public void setPartTime(boolean isPartTime) {
        this.isPartTime = isPartTime;
    }
}
