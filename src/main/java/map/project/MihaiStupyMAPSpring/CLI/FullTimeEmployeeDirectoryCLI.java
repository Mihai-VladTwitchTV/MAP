package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.FullTimeEmployee;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@ShellComponent
public class FullTimeEmployeeDirectoryCLI {

    @Autowired
    private EmployeeRepository employeeRepository;

    @ShellMethod(key = "list-full-time-employees", value = "List all full-time employees")
    public String listAllFullTimeEmployees() {
        Iterable<FullTimeEmployee> fullTimeEmployees = employeeRepository.findAllByIsFullTime(true);
        StringBuilder result = new StringBuilder("List of Full-Time Employees:\n");
        fullTimeEmployees.forEach(fullTimeEmployee -> {
            result.append("Employee ID: ").append(fullTimeEmployee.getEmployeeID()).append(", Name: ").append(fullTimeEmployee.getFirstName()).append(" ").append(fullTimeEmployee.getLastName()).append("\n");
        });
        return result.toString();
    }

    @ShellMethod(key = "add-full-time-employee", value = "Add a new full-time employee")
    public String addFullTimeEmployee(
            @ShellOption(value = "employeeID", help = "Employee ID") int employeeID,
            @ShellOption(value = "firstName", help = "First Name") String firstName,
            @ShellOption(value = "lastName", help = "Last Name") String lastName,
            @ShellOption(value = "phoneNumber", help = "Phone Number") int phoneNumber,
            @ShellOption(value = "emailAddress", help = "Email Address") String emailAddress,
            @ShellOption(value = "department", help = "Department") Department department) {

        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(employeeID, firstName, lastName, phoneNumber, emailAddress, department);
        employeeRepository.save(fullTimeEmployee);

        return "Full-Time Employee added successfully.";
    }
}
