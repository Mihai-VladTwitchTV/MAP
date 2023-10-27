package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DepartmentDirectoryCLI implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DepartmentDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Department Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all departments");
            System.out.println("2. Add a new department");
            System.out.println("3. Update a department");
            System.out.println("4. Delete a department");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllDepartments();
                    break;
                case 2:
                    addDepartment(scanner);
                    break;
                case 3:
                    updateDepartment(scanner);
                    break;
                case 4:
                    deleteDepartment(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllDepartments() {
        Iterable<Department> departments = departmentRepository.findAll();
        System.out.println("List of Departments:");
        departments.forEach(department -> System.out.println(department.getDepartmentID() + ": " + department.getSpecialization()));
    }

    private void addDepartment(Scanner scanner) {
        System.out.println("Enter department details:");

        // Get department details from the user
        System.out.print("Department ID: ");
        int departmentId = scanner.nextInt();

        System.out.print("Max Employees: ");
        int maxEmployees = scanner.nextInt();

        System.out.print("Specialization: ");
        String specialization = scanner.next();

        // Create a new department
        Department department = new Department(departmentId, maxEmployees, specialization);

        // Save the department to the database
        departmentRepository.save(department);

        System.out.println("Department added successfully.");
    }

    private void updateDepartment(Scanner scanner) {
        System.out.print("Enter department ID to update: ");
        int departmentId = scanner.nextInt();
        Department department = departmentRepository.findById(departmentId).orElse(null);

        if (department == null) {
            System.out.println("Department not found.");
            return;
        }

        System.out.println("Enter new department details:");

        System.out.print("Max Employees: ");
        int maxEmployees = scanner.nextInt();
        department.setMaxEmployees(maxEmployees);

        System.out.print("Specialization: ");
        String specialization = scanner.next();
        department.setSpecialization(specialization);

        // Save the updated department to the database
        departmentRepository.save(department);

        System.out.println("Department updated successfully.");
    }

    private void deleteDepartment(Scanner scanner) {
        System.out.print("Enter department ID to delete: ");
        int departmentId = scanner.nextInt();
        Department department = departmentRepository.findById(departmentId).orElse(null);

        if (department == null) {
            System.out.println("Department not found.");
            return;
        }

        // Delete the department from the database
        departmentRepository.delete(department);

        System.out.println("Department deleted successfully.");
    }
}

