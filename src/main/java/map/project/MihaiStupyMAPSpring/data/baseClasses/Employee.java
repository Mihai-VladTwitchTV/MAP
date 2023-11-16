package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Employee")
public class Employee {
    public Employee() {

    }///empty constructor used for database work

    public Employee(int employeeID, String firstName, String lastName, int phoneNumber, String emailAddress, Department department) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.department = department;
        this.type=null;
    }///detailed constructor used for initialization

    boolean isFullTime,isPartTime,isDepartmentLeader = false;


    @Setter
    @Getter
    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "employeeID")
    private int employeeID;


    @Setter
    @Getter
//    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "type")
    private String type;


    @Setter
    @Getter
    @Column(name = "firstName")
    private String firstName;

    @Setter
    @Getter
    @Column(name = "lastName")
    private String lastName;

    @Setter
    @Getter
    @Column(name = "phoneNumber")
    private int phoneNumber;

    @Setter
    @Getter
    @Column(name = "emailAddress")
    private String emailAddress;

    @Setter
    @ManyToOne
    @JoinColumn(name = "departmentID")
    private Department department;


    public int getDepartmentID() {
        return department.getDepartmentID();
    }

    public void setDepartmentID(int departmentID) {
        this.department.setDepartmentID(departmentID);
    }

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeSkill> skills;

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeProject> assignments;
}
