package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.*;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@SpringBootApplication
@ShellComponent
public class EmployeeDirectoryCLI {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    private String determineEmployeeRole(Employee employee) {
        if (employee.getType() != null) {
            switch (employee.getType().toLowerCase()) {
                case "full-time":
                    return "Full-Time";
                case "lead":
                    return "Lead";
                case "part-time":
                    return "Part-Time";
            }
        }
        return "Unknown";
    }


    @ShellMethod(key = "list-employees", value = "List all employees")
    public String listAllEmployees() {
        StringBuilder result = new StringBuilder("List of Employees:\n");
        Iterable<Employee> employees = employeeRepository.findAll();
        eventPublisher.publishRepositoryMethodEvent(this);

        employees.forEach(employee -> {
            String role = determineEmployeeRole(employee);
            result.append(employee.getEmployeeID()).append(": ")
                    .append(employee.getFirstName()).append(" ").append(employee.getLastName())
                    .append(" (Role: ").append(role).append(", ")
                    .append("Department: ").append(employee.getDepartment().getSpecialization()).append(')')
                    .append("\n");
        });

        return result.toString();
    }



    @ShellMethod(key = "add-employee", value = "Add a new employee")
    public String addEmployee(
            @ShellOption({"-id", "--employeeID"}) int employeeID,
            @ShellOption({"-first", "--firstName"}) String firstName,
            @ShellOption({"-last", "--lastName"}) String lastName,
            @ShellOption({"-phone", "--phoneNumber"}) int phoneNumber,
            @ShellOption({"-email", "--emailAddress"}) String emailAddress,
            @ShellOption({"-dept", "--department"}) int departmentID,
            @ShellOption({"-type", "--employeeType"}) String employeeType) {

        Department department = departmentRepository.findById(departmentID);
        if (department != null) {
            Employee employee;

            switch (employeeType.toLowerCase()) {
                case "full-time":
                    employee = new Employee.EmployeeBuilder(employeeID, firstName, lastName, phoneNumber, emailAddress, department)
                            .type("full-time")
                            .isFullTime(true)
                            .build();
                    break;
                case "lead":
                    employee = new Employee.EmployeeBuilder(employeeID, firstName, lastName, phoneNumber, emailAddress, department)
                            .type("lead")
                            .isLeader(true)
                            .build();
                    break;
                case "part-time":
                    employee = new Employee.EmployeeBuilder(employeeID, firstName, lastName, phoneNumber, emailAddress, department)
                            .type("part-time")
                            .build();
                    break;
                default:
                    return "Unknown employee type.";
            }

            employeeRepository.save(employee);
            return "Employee added successfully.";
        } else {
            return "Department not found.";
        }
    }



    @ShellMethod(key = "update-employee", value = "Update an employee")
    public String updateEmployee(@ShellOption({"-id", "--employeeID"}) int employeeID, @ShellOption({"-first", "--firstName"}) String firstName, @ShellOption({"-last", "--lastName"}) String lastName, @ShellOption({"-phone", "--phoneNumber"}) int phoneNumber, @ShellOption({"-email", "--emailAddress"}) String emailAddress, @ShellOption({"-dept", "--department"}) int departmentID) {
        Employee employee = employeeRepository.findById(employeeID).orElse(null);
        if (employee != null) {
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setPhoneNumber(phoneNumber);
            employee.setEmailAddress(emailAddress);
            eventPublisher.publishRepositoryMethodEvent(this);
            Department department = departmentRepository.findById(departmentID);
            if (department != null) {
                employee.setDepartment(department);
                eventPublisher.publishRepositoryMethodEvent(this);
                employeeRepository.save(employee);
                return "Employee updated successfully.";
            } else {
                return "Department not found.";
            }
        } else {
            return "Employee not found.";
        }
    }

    @ShellMethod(key = "delete-employee", value = "Delete an employee")
    public String deleteEmployee(@ShellOption({"-id", "--employeeID"}) int employeeID) {
        Employee employee = employeeRepository.findById(employeeID).orElse(null);
        if (employee != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            employeeRepository.delete(employee);
            return "Employee deleted successfully.";
        } else {
            return "Employee not found.";
        }
    }
}
