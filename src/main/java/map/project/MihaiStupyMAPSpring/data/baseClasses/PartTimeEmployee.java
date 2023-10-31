package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PartTimeEmployee")
public class PartTimeEmployee extends Employee implements parttime {
    public PartTimeEmployee() {
    }

    public PartTimeEmployee(int employeeID, String firstName, String lastName, int phoneNumber, String emailAddress, Department department) {
        super(employeeID, firstName, lastName, phoneNumber, emailAddress, department);
        isPartTime = true;
    }

    @Override
    public void setIsPartTime(boolean yn) {
        isPartTime = yn;
    }
}
