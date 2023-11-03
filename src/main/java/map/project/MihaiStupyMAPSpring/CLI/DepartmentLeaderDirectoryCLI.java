//package map.project.MihaiStupyMAPSpring.CLI;
//
//import map.project.MihaiStupyMAPSpring.data.baseClasses.DepartmentLeader;
//import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;
//
//import java.util.Scanner;
//
//@SpringBootApplication
//@ShellComponent
//public class DepartmentLeaderDirectoryCLI {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @ShellMethod(key = "list-department-leaders", value = "List all department leaders")
//    public String listAllDepartmentLeaders() {
//        StringBuilder result = new StringBuilder("List of Department Leaders:\n");
//        Iterable<DepartmentLeader> departmentLeaders = employeeRepository.findAllByIsDepartmentLeader(true);
//        departmentLeaders.forEach(leader -> result.append("Employee ID: ").append(leader.getEmployeeID()).append(", Name: ").append(leader.getFirstName()).append(" ").append(leader.getLastName()).append("\n"));
//        return result.toString();
//    }
//
//    @ShellMethod(key = "add-department-leader", value = "Add a new department leader")
//    public String addDepartmentLeader(@ShellOption({"-id", "--employeeID"}) int employeeID, @ShellOption({"-first", "--firstName"}) String firstName, @ShellOption({"-last", "--lastName"}) String lastName, @ShellOption({"-phone", "--phoneNumber"}) int phoneNumber, @ShellOption({"-email", "--emailAddress"}) String emailAddress) {
//        DepartmentLeader departmentLeader = new DepartmentLeader(employeeID, firstName, lastName, phoneNumber, emailAddress, null);
//        departmentLeader.setIsLeader(true);
//        employeeRepository.save(departmentLeader);
//        return "Department Leader added successfully.";
//    }
//}
