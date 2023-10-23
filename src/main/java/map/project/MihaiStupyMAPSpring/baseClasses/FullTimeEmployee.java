package map.project.MihaiStupyMAPSpring.baseClasses;

public class FullTimeEmployee extends Employee implements fulltime{
    public FullTimeEmployee(int employeeID, String firstName, String lastName, int phoneNumber, int emailAdress, int departmentID) {
        super(employeeID, firstName, lastName, phoneNumber, emailAdress, departmentID);
    }

}
