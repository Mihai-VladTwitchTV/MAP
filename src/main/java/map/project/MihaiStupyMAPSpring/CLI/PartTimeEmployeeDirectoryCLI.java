//package map.project.MihaiStupyMAPSpring.CLI;
//
//import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
//import map.project.MihaiStupyMAPSpring.data.baseClasses.PartTimeEmployee;
//import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;
//
//import java.util.Scanner;
//
//@ShellComponent
//public class PartTimeEmployeeDirectoryCLI {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @ShellMethod(key = "list-part-time-employees", value = "List all part-time employees")
//    public String listAllPartTimeEmployees() {
//        Iterable<Employee> partTimeEmployees = employeeRepository.findAllByIsPartTime(true);
//        StringBuilder result = new StringBuilder("List of Part-Time Employees:\n");
//        partTimeEmployees.forEach(partTimeEmployee -> {
//            result.append("Employee ID: ").append(partTimeEmployee.getEmployeeID()).append(", Name: ")
//                    .append(partTimeEmployee.getFirstName()).append(" ").append(partTimeEmployee.getLastName()).append("\n");
//        });
//        return result.toString();
//    }
//
//    @ShellMethod(key = "add-part-time-employee", value = "Add a new part-time employee")
//    public String addPartTimeEmployee(
//            @ShellOption(value = "employeeID", help = "Employee ID") int employeeID,
//            @ShellOption(value = "firstName", help = "First Name") String firstName,
//            @ShellOption(value = "lastName", help = "Last Name") String lastName,
//            @ShellOption(value = "phoneNumber", help = "Phone Number") int phoneNumber,
//            @ShellOption(value = "emailAddress", help = "Email Address") String emailAddress) {
//
//        PartTimeEmployee partTimeEmployee = new PartTimeEmployee(employeeID, firstName, lastName, phoneNumber, emailAddress, null);
//        partTimeEmployee.setIsPartTime(true);
//        employeeRepository.save(partTimeEmployee);
//
//        return "Part-Time Employee added successfully.";
//    }
//}
