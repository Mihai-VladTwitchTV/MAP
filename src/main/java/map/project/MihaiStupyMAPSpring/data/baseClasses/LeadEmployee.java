package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("LEAD")
public class LeadEmployee extends Employee implements Lead {
    public LeadEmployee() {
        setType("LEAD");
    }

    public LeadEmployee(int employeeID, String firstName, String lastName, int phoneNumber, String emailAddress, Department department) {
        super(employeeID, firstName, lastName, phoneNumber, emailAddress, department);
        setType("LEAD");
    }

    @Override
    public void setLeader(boolean isLeader) {
        this.isDepartmentLeader = isLeader;
    }
}
