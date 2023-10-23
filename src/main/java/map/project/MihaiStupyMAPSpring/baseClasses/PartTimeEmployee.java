package map.project.MihaiStupyMAPSpring.baseClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PartTimeEmployee")
public class PartTimeEmployee extends Employee implements parttime {
    public PartTimeEmployee() {
    }

    public PartTimeEmployee(int employeeID, String firstName, String lastName, int phoneNumber, int emailAdress, Department department) {
        super(employeeID, firstName, lastName, phoneNumber, emailAdress, department);
    }
}
