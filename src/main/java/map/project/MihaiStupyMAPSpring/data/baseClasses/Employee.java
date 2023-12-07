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
    }

    public static class EmployeeBuilder {
        private int employeeID;
        private String type;
        private String firstName;
        private String lastName;
        private int phoneNumber;
        private String emailAddress;
        private Department department;
        private boolean isFullTime;
        private boolean isPartTime;
        private boolean isLeader;

        public EmployeeBuilder(int employeeID, String firstName, String lastName, int phoneNumber, String emailAddress, Department department) {
            this.employeeID = employeeID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
            this.department = department;
        }

        public EmployeeBuilder type(String type) {
            this.type = type;
            return this;
        }

        public EmployeeBuilder isFullTime(boolean isFullTime) {
            this.isFullTime = isFullTime;
            return this;
        }

        public EmployeeBuilder isPartTime(boolean isPartTime) {
            this.isPartTime = isPartTime;
            return this;
        }

        public EmployeeBuilder isLeader(boolean isLeader) {
            this.isLeader = isLeader;
            return this;
        }

        public Employee build() {
            Employee employee;

            if (isFullTime) {
                employee = new FullTimeEmployee();
                ((FullTimeEmployee) employee).setIsFullTime(true);
            } else if (isPartTime) {
                employee = new PartTimeEmployee();
                ((PartTimeEmployee) employee).setIsPartTime(true);
            } else if (isLeader) {
                employee = new LeadEmployee();
                ((LeadEmployee) employee).setIsLeader(true);
            } else {
                employee = new Employee();
            }

            // Set common attributes
            employee.setEmployeeID(employeeID);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setPhoneNumber(phoneNumber);
            employee.setEmailAddress(emailAddress);
            employee.setDepartment(department);
            employee.setType(type);
            employee.setSkills(null); // You may need to set skills and assignments accordingly
            employee.setAssignments(null);

            return employee;
        }
    }

    @Setter
    @Getter
    @Id
    @Column(name = "employeeID")
    private int employeeID;

    @Setter
    @Getter
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

    @Setter
    @Getter
    @Column(name = "isFullTime")
    private boolean isFullTime;

    @Setter
    @Getter
    @Column(name = "isPartTime")
    private boolean isPartTime;

    @Setter
    @Getter
    @OneToMany(mappedBy = "employee")
    private Set<EmployeeSkill> skills;

//    @Setter
//    @Getter
//    @OneToMany(mappedBy = "employee")
//    private Set<Assignments> assignments;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "assignmentid")
    private Assignments assignments;
}