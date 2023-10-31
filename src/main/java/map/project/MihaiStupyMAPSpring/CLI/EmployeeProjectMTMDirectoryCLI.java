package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeProject;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeProjectRepository;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EmployeeProjectMTMDirectoryCLI implements CommandLineRunner {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeProjectMTMDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Employee-Project Relationship Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all employee-project relationships");
            System.out.println("2. Add a new employee-project relationship");
            System.out.println("3. Delete an employee-project relationship");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllEmployeeProjectRelationships();
                    break;
                case 2:
                    addEmployeeProjectRelationship(scanner);
                    break;
                case 3:
                    deleteEmployeeProjectRelationship(scanner);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllEmployeeProjectRelationships() {
        Iterable<EmployeeProject> employeeProjects = employeeProjectRepository.findAll();
        System.out.println("List of Employee-Project Relationships:");
        employeeProjects.forEach(employeeProject -> {
            Employee employee = employeeProject.getEmployee();
            Project project = employeeProject.getProject();
            System.out.println("Employee ID: " + employee.getEmployeeID() + ", Project ID: " + project.getProjectID());
        });
    }

    private void addEmployeeProjectRelationship(Scanner scanner) {
        System.out.println("Enter employee-project relationship details:");

        // Get employee and project details from the user
        Employee employee = createEmployee(scanner);
        Project project = createProject(scanner);

        // Create a new employee-project relationship
        EmployeeProject employeeProject = new EmployeeProject(employee, project, null);

        // Save the employee-project relationship to the database
        employeeProjectRepository.save(employeeProject);

        System.out.println("Employee-Project Relationship added successfully.");
    }

    private void deleteEmployeeProjectRelationship(Scanner scanner) {
        System.out.println("Enter employee ID to delete the relationship:");
        int employeeID = scanner.nextInt();
        Employee employee = employeeRepository.findById(employeeID).orElse(null);

        System.out.println("Enter project ID to delete the relationship:");
        int projectID = scanner.nextInt();
        Project project = projectRepository.findById(projectID).orElse(null);

        if (employee == null || project == null) {
            System.out.println("Employee or Project not found. Unable to delete the relationship.");
            return;
        }

        EmployeeProject employeeProject = new EmployeeProject(employee, project, null);

        // Delete the employee-project relationship from the database
        employeeProjectRepository.delete(employeeProject);

        System.out.println("Employee-Project Relationship deleted successfully.");
    }

    private Employee createEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();

        Employee employee = employeeRepository.findById(employeeID).orElse(null);

        if (employee == null) {
            System.out.println("Employee not found. Please add the employee first.");
        }

        return employee;
    }

    private Project createProject(Scanner scanner) {
        System.out.print("Enter Project ID: ");
        int projectID = scanner.nextInt();

        Project project = projectRepository.findById(projectID).orElse(null);

        if (project == null) {
            System.out.println("Project not found. Please add the project first.");
        }

        return project;
    }
}
