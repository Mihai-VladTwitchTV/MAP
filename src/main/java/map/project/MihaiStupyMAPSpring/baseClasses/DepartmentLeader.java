package map.project.MihaiStupyMAPSpring.baseClasses;

public class DepartmentLeader extends Employee implements lead {
    public DepartmentLeader(int employeeID, String firstName, String lastName, int phoneNumber, int emailAdress, int departmentID) {
        super(employeeID, firstName, lastName, phoneNumber, emailAdress, departmentID);
    }

    @Override
    public void doLeaderStuff() {
        System.out.println("Im a leader");

    }
}
