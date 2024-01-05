package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.*;
import map.project.MihaiStupyMAPSpring.service.AssignmentsService;
import map.project.MihaiStupyMAPSpring.service.DepartmentService;

import java.util.Collections;

public class EmployeeDTO {
    private int employeeID;
    private String type;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String emailAddress;
    private int departmentId; // Assuming we only need the department ID
    private boolean isFullTime;
    private boolean isPartTime;
    private boolean isLeader;
    private int assignmentId; // Assuming we only need the assignment ID

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setFullTime(boolean fullTime) {
        isFullTime = fullTime;
    }

    public boolean isPartTime() {
        return isPartTime;
    }

    public void setPartTime(boolean partTime) {
        isPartTime = partTime;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Employee toEmployee(DepartmentService departmentService, AssignmentsService assignmentsService) {
        Department department = departmentService.findDepartmentById(this.departmentId);
        Assignments assignment = assignmentsService.findById(this.assignmentId);

        switch (this.type.toLowerCase()) {
            case "full-time":
                FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
                fullTimeEmployee.setIsFullTime(true);
                populateCommonEmployeeFields(fullTimeEmployee, department, assignment);
                return fullTimeEmployee;
            case "lead":
                LeadEmployee leadEmployee = new LeadEmployee();
                leadEmployee.setIsLeader(true);
                populateCommonEmployeeFields(leadEmployee, department, assignment);
                return leadEmployee;
            case "part-time":
                PartTimeEmployee partTimeEmployee = new PartTimeEmployee();
                partTimeEmployee.setIsPartTime(true);
                populateCommonEmployeeFields(partTimeEmployee, department, assignment);
                return partTimeEmployee;
            default:
                throw new IllegalArgumentException("Unknown employee type.");
        }
    }
    private void populateCommonEmployeeFields(Employee employee, Department department, Assignments assignment) {
        employee.setEmployeeID(this.employeeID);
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setPhoneNumber(this.phoneNumber);
        employee.setEmailAddress(this.emailAddress);
        employee.setDepartment(department);
        employee.setAssignments(assignment);
    }

    public Employee updateEmployee(Employee existingEmployee, DepartmentService departmentService, AssignmentsService assignmentsService) {
        // Update basic fields
        existingEmployee.setFirstName(this.firstName);
        existingEmployee.setLastName(this.lastName);
        existingEmployee.setPhoneNumber(this.phoneNumber);
        existingEmployee.setEmailAddress(this.emailAddress);

        // Update department
        existingEmployee.setDepartment(departmentService.findDepartmentById(this.departmentId));

        // Update type-specific fields
        switch (this.type.toLowerCase()) {
            case "full-time":
                existingEmployee.setFullTime(true);
                existingEmployee.setPartTime(false);
                existingEmployee.setLeader(false);
                // Additional logic for full-time specific fields, if any
                break;
            case "lead":
                existingEmployee.setLeader(true);
                existingEmployee.setFullTime(false);
                existingEmployee.setPartTime(false);
                // Additional logic for lead specific fields, if any
                break;
            case "part-time":
                existingEmployee.setPartTime(true);
                existingEmployee.setFullTime(false);
                existingEmployee.setLeader(false);
                // Additional logic for part-time specific fields, if any
                break;
            default:
                throw new IllegalArgumentException("Unknown employee type.");
        }

        // Update assignments
        existingEmployee.setAssignments(assignmentsService.findById(this.assignmentId));

        return existingEmployee;
    }
}
