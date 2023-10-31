package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.PartTimeEmployee;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PartTimeEmployeeDirectoryCLI implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(PartTimeEmployeeDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Part-Time Employee Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all part-time employees");
            System.out.println("2. Add a new part-time employee");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllPartTimeEmployees();
                    break;
                case 2:
                    addPartTimeEmployee(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllPartTimeEmployees() {
        Iterable<Employee> partTimeEmployees = employeeRepository.findAllByIsPartTime(true);
        System.out.println("List of Part-Time Employees:");
        partTimeEmployees.forEach(partTimeEmployee -> {
            System.out.println("Employee ID: " + partTimeEmployee.getEmployeeID() + ", Name: " + partTimeEmployee.getFirstName() + " " + partTimeEmployee.getLastName());
        });
    }

    private void addPartTimeEmployee(Scanner scanner) {
        System.out.println("Enter part-time employee details:");

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

        // Create a new part-time employee
        PartTimeEmployee partTimeEmployee = new PartTimeEmployee(employeeID, firstName, lastName, phoneNumber, emailAddress, null);

        // Set the isPartTime property to true for part-time employees
        partTimeEmployee.setIsPartTime(true);

        // Save the part-time employee to the database
        employeeRepository.save(partTimeEmployee);

        System.out.println("Part-Time Employee added successfully.");
    }
}
