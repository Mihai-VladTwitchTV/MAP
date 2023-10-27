package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Department")
public class Department {
    public Department(int departmentID, int maxEmployees, String specialization /*, DepartmentLeader leader */) {
        this.departmentID = departmentID;
        this.maxEmployees = maxEmployees;
        this.specialization = specialization;
        //this.leader = leader; this is bad because it creates a loop of dependencies with Departments and Employees(dep.leader)
    }///Detailed constructor for class initialization

    public Department() {
    }///Empty constructor for database work

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter
    @Setter
    @Column(name = "departmentID")
    private int departmentID;

    @Getter
    @Setter
    @Column(name = "maxEmployees")
    private int maxEmployees;
    @Column(name = "specialization")
    private String specialization;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "leaderID")
    private DepartmentLeader leader;

}
