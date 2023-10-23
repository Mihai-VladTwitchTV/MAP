package map.project.MihaiStupyMAPSpring.baseClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DepartmentLeader")
public class DepartmentLeader extends Employee implements lead {
    public DepartmentLeader(int employeeID, String firstName, String lastName, int phoneNumber, int emailAdress, Department department) {
        super(employeeID, firstName, lastName, phoneNumber, emailAdress, department);
    }///used for initialization

    public DepartmentLeader() {
    }///used when working with databases

    @Override
    public void doLeaderStuff() {
        System.out.println("Im a leader");

    }
}
