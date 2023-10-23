package map.project.MihaiStupyMAPSpring.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Table(name = "Employee")
public class Employee {
    public Employee() {

    }///empty constructor used for database work

    public Employee(int employeeID, String firstName, String lastName, int phoneNumber, int emailAdress, Department department) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
        this.department = department;
    }///detailed constructor used for initialization

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "employeeID")
    private int employeeID;

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
    @Column(name = "emailAdress")
    private int emailAdress;

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
}
