package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.DepartmentLeader;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentLeaderRepository;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DepartmentLeaderDirectoryCLI implements CommandLineRunner {

    @Autowired
    private DepartmentLeaderRepository departmentLeaderRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DepartmentLeaderDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Department Leader Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all department leaders");
            System.out.println("2. Add a new department leader");
            System.out.println("3. Update a department leader");
            System.out.println("4. Delete a department leader");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllDepartmentLeaders();
                    break;
                case 2:
                    addDepartmentLeader(scanner);
                    break;
                case 3:
                    updateDepartmentLeader(scanner);
                    break;
                case 4:
                    deleteDepartmentLeader(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllDepartmentLeaders() {
        Iterable<DepartmentLeader> departmentLeaders = departmentLeaderRepository.findAll();
        System.out.println("List of Department Leaders:");
        departmentLeaders.forEach(leader -> System.out.println(leader.getEmployeeID() + ": " + leader.getFirstName() + " " + leader.getLastName()));
    }

    private void addDepartmentLeader(Scanner scanner) {
        System.out.println("Enter department leader details:");

        // Get department leader details from the user
        System.out.print("Employee ID: ");
        int employeeId = scanner.nextInt();

        System.out.print("First Name: ");
        String firstName = scanner.next();

        System.out.print("Last Name: ");
        String lastName = scanner.next();

        System.out.print("Phone Number: ");
        int phoneNumber = scanner.nextInt();

        System.out.print("Email Address: ");
        String emailAddress = scanner.next();

        // Create a new department
        Department department = createDepartment(scanner);

        // Create a new department leader
        DepartmentLeader leader = new DepartmentLeader(employeeId, firstName, lastName, phoneNumber, emailAddress, department);

        // Save the department leader to the database
        departmentLeaderRepository.save(leader);

        System.out.println("Department Leader added successfully.");
    }

    private void updateDepartmentLeader(Scanner scanner) {
        System.out.print("Enter department leader ID to update: ");
        int employeeId = scanner.nextInt();
        DepartmentLeader leader = departmentLeaderRepository.findById(employeeId).orElse(null);

        if (leader == null) {
            System.out.println("Department Leader not found.");
            return;
        }

        System.out.println("Enter new department leader details:");

        System.out.print("First Name: ");
        String firstName = scanner.next();
        leader.setFirstName(firstName);

        System.out.print("Last Name: ");
        String lastName = scanner.next();
        leader.setLastName(lastName);

        System.out.print("Phone Number: ");
        int phoneNumber = scanner.nextInt();
        leader.setPhoneNumber(phoneNumber);

        System.out.print("Email Address: ");
        String emailAddress = scanner.next();
        leader.setEmailAddress(emailAddress);

        // Update the department
        Department department = createDepartment(scanner);
        leader.setDepartment(department);

        // Save the updated department leader to the database
        departmentLeaderRepository.save(leader);

        System.out.println("Department Leader updated successfully.");
    }

    private void deleteDepartmentLeader(Scanner scanner) {
        System.out.print("Enter department leader ID to delete: ");
        int employeeId = scanner.nextInt();
        DepartmentLeader leader = departmentLeaderRepository.findById(employeeId).orElse(null);

        if (leader == null) {
            System.out.println("Department Leader not found.");
            return;
        }

        // Delete the department leader from the database
        departmentLeaderRepository.delete(leader);

        System.out.println("Department Leader deleted successfully.");
    }

    private Department createDepartment(Scanner scanner) {
        System.out.println("Enter department details:");

        System.out.print("Department ID: ");
        int departmentId = scanner.nextInt();

        System.out.print("Max Employees: ");
        int maxEmployees = scanner.nextInt();

        System.out.print("Specialization: ");
        String specialization = scanner.next();

        Department department = new Department(departmentId, maxEmployees, specialization);

        // Save the department to the database
        departmentRepository.save(department);

        return department;
    }
}

