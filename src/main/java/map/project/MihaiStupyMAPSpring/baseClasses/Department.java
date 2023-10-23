package map.project.MihaiStupyMAPSpring.baseClasses;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "departmentID")
    private int departmentID;

    @Column(name = "maxEmployees")
    private int maxEmployees;
    @Column(name = "specialization")
    private String specialization;

    @OneToOne
    @JoinColumn(name = "leaderID")
    private DepartmentLeader leader;

}
