package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class ProjectDirectoryCLI implements CommandLineRunner {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Project Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all projects");
            System.out.println("2. Add a new project");
            System.out.println("3. Update a project");
            System.out.println("4. Delete a project");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllProjects();
                    break;
                case 2:
                    addProject(scanner);
                    break;
                case 3:
                    updateProject(scanner);
                    break;
                case 4:
                    deleteProject(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllProjects() {
        Iterable<Project> projects = projectRepository.findAll();
        System.out.println("List of Projects:");
        projects.forEach(project -> System.out.println(project.getProjectID() + ": " + project.getProjectName()));
    }

    private void addProject(Scanner scanner) {
        System.out.println("Enter project details:");

        // Get project details from the user
        System.out.print("Project ID: ");
        int projectID = scanner.nextInt();

        Client client = createClient(scanner);
        Department department = createDepartment(scanner);

        System.out.print("Project Name: ");
        String projectName = scanner.next();

        System.out.print("Start Date (yyyy-MM-dd HH:mm:ss): ");
        Date startDate = parseDate(scanner.next());

        System.out.print("End Date (yyyy-MM-dd HH:mm:ss): ");
        Date endDate = parseDate(scanner.next());

        System.out.print("Status: ");
        String status = scanner.next();

        System.out.print("Meeting Type: ");
        String meetingType = scanner.next();

        // Create a new project
        Project project = new Project(projectID, client, department, projectName, startDate, endDate, status, meetingType, null, null, null);

        // Save the project to the database
        projectRepository.save(project);

        System.out.println("Project added successfully.");
    }

    private void updateProject(Scanner scanner) {
        System.out.print("Enter project ID to update: ");
        int projectID = scanner.nextInt();
        Project project = projectRepository.findById(projectID).orElse(null);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Enter new project details:");

        Client client = createClient(scanner);
        project.setClient(client);

        Department department = createDepartment(scanner);
        project.setDepartment(department);

        System.out.print("Project Name: ");
        String projectName = scanner.next();
        project.setProjectName(projectName);

        System.out.print("Start Date (yyyy-MM-dd HH:mm:ss): ");
        Date startDate = parseDate(scanner.next());
        project.setStartDate(startDate);

        System.out.print("End Date (yyyy-MM-dd HH:mm:ss): ");
        Date endDate = parseDate(scanner.next());
        project.setEndDate(endDate);

        System.out.print("Status: ");
        String status = scanner.next();
        project.setStatus(status);

        System.out.print("Meeting Type: ");
        String meetingType = scanner.next();
        project.setMeetingType(meetingType);

        // Save the updated project to the database
        projectRepository.save(project);

        System.out.println("Project updated successfully.");
    }

    private void deleteProject(Scanner scanner) {
        System.out.print("Enter project ID to delete: ");
        int projectID = scanner.nextInt();
        Project project = projectRepository.findById(projectID).orElse(null);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        // Delete the project from the database
        projectRepository.delete(project);

        System.out.println("Project deleted successfully.");
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Client createClient(Scanner scanner) {
        System.out.print("Client ID: ");
        int clientID = scanner.nextInt();

        Client client = new Client();
        client.setClientID(clientID);

        return client;
    }

    private Department createDepartment(Scanner scanner) {
        System.out.print("Department ID: ");
        int departmentID = scanner.nextInt();

        Department department = new Department();
        department.setDepartmentID(departmentID);

        return department;
    }
}
