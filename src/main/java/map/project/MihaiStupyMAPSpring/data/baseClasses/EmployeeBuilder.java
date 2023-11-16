//package map.project.MihaiStupyMAPSpring.data.baseClasses;
//
//public class EmployeeBuilder {
//    private int employeeID;
//    private String firstName;
//    private String lastName;
//    private int phoneNumber;
//    private String emailAddress;
//    private Department department;
//
//    // Additional fields specific to each employee type
//    private boolean isFullTime;
//    private boolean isPartTime;
//    private boolean isLead;
//
//    public EmployeeBuilder setEmployeeID(int employeeID) {
//        this.employeeID = employeeID;
//        return this;
//    }
//
//    public EmployeeBuilder setFirstName(String firstName) {
//        this.firstName = firstName;
//        return this;
//    }
//
//    public EmployeeBuilder setLastName(String lastName) {
//        this.lastName = lastName;
//        return this;
//    }
//
//    public EmployeeBuilder setPhoneNumber(int phoneNumber) {
//        this.phoneNumber = phoneNumber;
//        return this;
//    }
//
//    public EmployeeBuilder setEmailAddress(String emailAddress) {
//        this.emailAddress = emailAddress;
//        return this;
//    }
//
//    public EmployeeBuilder setDepartment(Department department) {
//        this.department = department;
//        return this;
//    }
//
//    // Set additional fields for FullTimeEmployee
//    public EmployeeBuilder setFullTime(boolean isFullTime) {
//        this.isFullTime = isFullTime;
//        return this;
//    }
//
//    // Set additional fields for PartTimeEmployee
//    public EmployeeBuilder setPartTime(boolean isPartTime) {
//        this.isPartTime = isPartTime;
//        return this;
//    }
//
//    // Set additional fields for LeadEmployee
//    public EmployeeBuilder setLead(boolean isLead) {
//        this.isLead = isLead;
//        return this;
//    }
//
//    public FullTimeEmployee buildFullTimeEmployee() {
//        return new FullTimeEmployee(employeeID, firstName, lastName, phoneNumber, emailAddress, department, isFullTime);
//    }
//
//    public PartTimeEmployee buildPartTimeEmployee() {
//        return new PartTimeEmployee(employeeID, firstName, lastName, phoneNumber, emailAddress, department, isPartTime);
//    }
//
//    public LeadEmployee buildLeadEmployee() {
//        return new LeadEmployee(employeeID, firstName, lastName, phoneNumber, emailAddress, department, isLead);
//    }
//}
