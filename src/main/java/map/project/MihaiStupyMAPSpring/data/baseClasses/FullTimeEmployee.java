package map.project.MihaiStupyMAPSpring.baseClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "FullTimeEmployee")
//Test daca merge sa dau pull de la Stupy
public class FullTimeEmployee extends Employee implements fulltime{
    public FullTimeEmployee() {
    }

    public FullTimeEmployee(int employeeID, String firstName, String lastName, int phoneNumber, String emailAdress, Department department) {
        super(employeeID, firstName, lastName, phoneNumber, emailAdress, department);
    }
}
