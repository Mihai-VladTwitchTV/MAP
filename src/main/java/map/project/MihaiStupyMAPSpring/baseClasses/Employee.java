package map.project.MihaiStupyMAPSpring.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;


@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "employeeID")
    private int employeeID;
    @Getter
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phoneNumber")
    private int phoneNumber;
    @Column(name = "emailAdress")
    private int emailAdress;

    @JoinColumn(name = "departmentID")
    @ManyToOne
    private Department department;


    public String getLastName() {
        return lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getEmailAdress() {
        return emailAdress;
    }

    public int getDepartmentID() {
        return department.getDepartmentID();
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAdress(int emailAdress) {
        this.emailAdress = emailAdress;
    }

    public void setDepartmentID(int departmentID) {
        this.department.setDepartmentID(departmentID);
    }
}
