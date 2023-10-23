package map.project.MihaiStupyMAPSpring.baseClasses;

public class PartTimeEmployee extends Employee implements parttime {

    public PartTimeEmployee(int employeeID, String firstName, String lastName, int phoneNumber, int emailAdress, int departmentID) {
        super(employeeID, firstName, lastName, phoneNumber, emailAdress, departmentID);
    }
}
