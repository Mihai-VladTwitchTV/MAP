package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AssignmentsDirectoryCLI implements CommandLineRunner {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    public static void main(String[] args) {
        SpringApplication.run(AssignmentsDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Assignments Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all assignments");
            System.out.println("2. Add a new assignment");
            System.out.println("3. Update an assignment");
            System.out.println("4. Delete an assignment");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllAssignments();
                    break;
                case 2:
                    addAssignment(scanner);
                    break;
                case 3:
                    updateAssignment(scanner);
                    break;
                case 4:
                    deleteAssignment(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllAssignments() {
        Iterable<Assignments> assignments = assignmentsRepository.findAll();
        System.out.println("List of Assignments:");
        assignments.forEach(assignment -> System.out.println(assignment.getId() + ": " + assignment.getAssignmentName()));
    }

    private void addAssignment(Scanner scanner) {
        System.out.println("Enter assignment details:");

        // Get assignment details from the user
        System.out.print("Assignment ID: ");
        int assignmentId = scanner.nextInt();

        System.out.print("Assignment Name: ");
        String assignmentName = scanner.next();

        // Create a new assignment
        Assignments assignment = new Assignments(assignmentId, assignmentName);

        // Save the assignment to the database
        assignmentsRepository.save(assignment);

        System.out.println("Assignment added successfully.");
    }

    private void updateAssignment(Scanner scanner) {
        System.out.print("Enter assignment ID to update: ");
        int assignmentId = scanner.nextInt();
        Assignments assignment = assignmentsRepository.findById(assignmentId).orElse(null);

        if (assignment == null) {
            System.out.println("Assignment not found.");
            return;
        }

        System.out.println("Enter new assignment details:");

        System.out.print("Assignment Name: ");
        String assignmentName = scanner.next();
        assignment.setAssignmentName(assignmentName);

        // Save the updated assignment to the database
        assignmentsRepository.save(assignment);

        System.out.println("Assignment updated successfully.");
    }

    private void deleteAssignment(Scanner scanner) {
        System.out.print("Enter assignment ID to delete: ");
        int assignmentId = scanner.nextInt();
        Assignments assignment = assignmentsRepository.findById(assignmentId).orElse(null);

        if (assignment == null) {
            System.out.println("Assignment not found.");
            return;
        }

        // Delete the assignment from the database
        assignmentsRepository.delete(assignment);

        System.out.println("Assignment deleted successfully.");
    }
}
