package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.FullTimeEmployee;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FullTimeEmployeeDirectoryCLI implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(FullTimeEmployeeDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Full-Time Employee Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all full-time employees");
            System.out.println("2. Add a new full-time employee");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllFullTimeEmployees();
                    break;
                case 2:
                    addFullTimeEmployee(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllFullTimeEmployees() {
        // Use the EmployeeRepository to retrieve FullTimeEmployee instances
        Iterable<FullTimeEmployee> fullTimeEmployees = employeeRepository.findAllByIsFullTime(true);
        System.out.println("List of Full-Time Employees:");
        fullTimeEmployees.forEach(fullTimeEmployee -> {
            System.out.println("Employee ID: " + fullTimeEmployee.getEmployeeID() + ", Name: " + fullTimeEmployee.getFirstName() + " " + fullTimeEmployee.getLastName());
        });
    }

    private void addFullTimeEmployee(Scanner scanner) {
        System.out.println("Enter full-time employee details:");

        // Get employee details from the user
        System.out.print("Employee ID: ");
        int employeeID = scanner.nextInt();

        System.out.print("First Name: ");
        String firstName = scanner.next();

        System.out.print("Last Name: ");
        String lastName = scanner.next();

        System.out.print("Phone Number: ");
        int phoneNumber = scanner.nextInt();

        System.out.print("Email Address: ");
        String emailAddress = scanner.next();

        // Create a new full-time employee
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(employeeID, firstName, lastName, phoneNumber, emailAddress, null);
        fullTimeEmployee.setIsFullTime(true);

        // Save the full-time employee to the database using the EmployeeRepository
        employeeRepository.save(fullTimeEmployee);

        System.out.println("Full-Time Employee added successfully.");
    }
}
