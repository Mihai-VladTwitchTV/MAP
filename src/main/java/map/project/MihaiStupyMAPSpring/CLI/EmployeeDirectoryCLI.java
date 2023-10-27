package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EmployeeDirectoryCLI implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Employee Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all employees");
            System.out.println("2. Add a new employee");
            System.out.println("3. Update an employee");
            System.out.println("4. Delete an employee");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllEmployees();
                    break;
                case 2:
                    addEmployee(scanner);
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllEmployees() {
        Iterable<Employee> employees = employeeRepository.findAll();
        System.out.println("List of Employees:");
        employees.forEach(employee -> System.out.println(employee.getEmployeeID() + ": " + employee.getFirstName() + " " + employee.getLastName()));
    }

    private void addEmployee(Scanner scanner) {
        System.out.println("Enter employee details:");

        // Get employee details from the user
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

        // Create a new employee
        Employee employee = new Employee(employeeId, firstName, lastName, phoneNumber, emailAddress, department);

        // Save the employee to the database
        employeeRepository.save(employee);

        System.out.println("Employee added successfully.");
    }

    private void updateEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to update: ");
        int employeeId = scanner.nextInt();
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Enter new employee details:");

        System.out.print("First Name: ");
        String firstName = scanner.next();
        employee.setFirstName(firstName);

        System.out.print("Last Name: ");
        String lastName = scanner.next();
        employee.setLastName(lastName);

        System.out.print("Phone Number: ");
        int phoneNumber = scanner.nextInt();
        employee.setPhoneNumber(phoneNumber);

        System.out.print("Email Address: ");
        String emailAddress = scanner.next();
        employee.setEmailAddress(emailAddress);

        // Update the department
        Department department = createDepartment(scanner);
        employee.setDepartment(department);

        // Save the updated employee to the database
        employeeRepository.save(employee);

        System.out.println("Employee updated successfully.");
    }

    private void deleteEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to delete: ");
        int employeeId = scanner.nextInt();
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        // Delete the employee from the database
        employeeRepository.delete(employee);

        System.out.println("Employee deleted successfully.");
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
