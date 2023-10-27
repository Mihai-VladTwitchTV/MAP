package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.baseClasses.ProjectMilestones;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectMilestonesRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ProjectMilestonesDirectoryCLI implements CommandLineRunner {

    @Autowired
    private ProjectMilestonesRepository projectMilestonesRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectMilestonesDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Project Milestones Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all project milestones");
            System.out.println("2. Add a new project milestone");
            System.out.println("3. Update a project milestone");
            System.out.println("4. Delete a project milestone");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllProjectMilestones();
                    break;
                case 2:
                    addProjectMilestone(scanner);
                    break;
                case 3:
                    updateProjectMilestone(scanner);
                    break;
                case 4:
                    deleteProjectMilestone(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllProjectMilestones() {
        Iterable<ProjectMilestones> projectMilestones = projectMilestonesRepository.findAll();
        System.out.println("List of Project Milestones:");
        projectMilestones.forEach(milestone -> System.out.println(milestone.getMilestoneID() + ": " + milestone.getMilestoneName()));
    }

    private void addProjectMilestone(Scanner scanner) {
        System.out.println("Enter project milestone details:");

        // Get project milestone details from the user
        System.out.print("Milestone ID: ");
        int milestoneID = scanner.nextInt();

        Project project = createProject(scanner);

        System.out.print("Milestone Name: ");
        String milestoneName = scanner.next();

        System.out.print("Description: ");
        String description = scanner.next();

        // Create a new project milestone
        ProjectMilestones milestone = new ProjectMilestones(milestoneID, project, milestoneName, description);

        // Save the project milestone to the database
        projectMilestonesRepository.save(milestone);

        System.out.println("Project milestone added successfully.");
    }

    private void updateProjectMilestone(Scanner scanner) {
        System.out.print("Enter project milestone ID to update: ");
        int milestoneID = scanner.nextInt();
        ProjectMilestones milestone = projectMilestonesRepository.findById(milestoneID).orElse(null);

        if (milestone == null) {
            System.out.println("Project milestone not found.");
            return;
        }

        System.out.println("Enter new project milestone details:");

        Project project = createProject(scanner);
        milestone.setProject(project);

        System.out.print("Milestone Name: ");
        String milestoneName = scanner.next();
        milestone.setMilestoneName(milestoneName);

        System.out.print("Description: ");
        String description = scanner.next();
        milestone.setDescription(description);

        // Save the updated project milestone to the database
        projectMilestonesRepository.save(milestone);

        System.out.println("Project milestone updated successfully.");
    }

    private void deleteProjectMilestone(Scanner scanner) {
        System.out.print("Enter project milestone ID to delete: ");
        int milestoneID = scanner.nextInt();
        ProjectMilestones milestone = projectMilestonesRepository.findById(milestoneID).orElse(null);

        if (milestone == null) {
            System.out.println("Project milestone not found.");
            return;
        }

        // Delete the project milestone from the database
        projectMilestonesRepository.delete(milestone);

        System.out.println("Project milestone deleted successfully.");
    }

    private Project createProject(Scanner scanner) {
        System.out.print("Project ID: ");
        int projectID = scanner.nextInt();

        Project project = projectRepository.findById(projectID).orElse(null);

        if (project == null) {
            System.out.println("Project not found. Please add the project first.");
        }

        return project;
    }
}
