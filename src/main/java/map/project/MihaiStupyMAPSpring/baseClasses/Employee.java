package map.project.MihaiStupyMAPSpring.baseClasses;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "employeeID")
    private int employeeID;
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
    private int departmentID;



    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

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
        return departmentID;
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
        this.departmentID = departmentID;
    }
}
