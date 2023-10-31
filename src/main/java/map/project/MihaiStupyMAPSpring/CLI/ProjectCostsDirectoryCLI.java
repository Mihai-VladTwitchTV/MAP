package map.project.MihaiStupyMAPSpring.CLI;


import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.baseClasses.ProjectCosts;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectCostsRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@SpringBootApplication
public class ProjectCostsDirectoryCLI implements CommandLineRunner {

    @Autowired
    private ProjectCostsRepository projectCostsRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectCostsDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Project Costs Management CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List project costs");
            System.out.println("2. Add project cost");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listProjectCosts();
                    break;
                case 2:
                    addProjectCost(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listProjectCosts() {
        System.out.println("List of Project Costs:");
        Iterable<ProjectCosts> projectCosts = projectCostsRepository.findAll();
        projectCosts.forEach(this::printProjectCost);
    }

    private void printProjectCost(ProjectCosts projectCost) {
        System.out.println("Cost ID: " + projectCost.getCostID());
        System.out.println("Project ID: " + projectCost.getProject().getProjectID());
        System.out.println("Expense Type: " + projectCost.getExpenseType());
        System.out.println("Description: " + projectCost.getDescription());
        System.out.println("Amount: " + projectCost.getAmount());
        System.out.println("Due Date: " + formatDate(projectCost.getDueDate()));
        System.out.println("=================================");
    }

    private void addProjectCost(Scanner scanner) {
        System.out.print("Enter Project ID: ");
        int projectID = scanner.nextInt();
        Project project = projectRepository.findById(projectID).orElse(null);

        if (project != null) {
            System.out.print("Enter Expense Type: ");
            String expenseType = scanner.next();

            System.out.print("Enter Description: ");
            String description = scanner.next();

            System.out.print("Enter Amount: ");
            BigDecimal amount = scanner.nextBigDecimal();

            System.out.print("Enter Due Date (yyyy-MM-dd): ");
            String dueDateString = scanner.next();
            Date dueDate = parseDate(dueDateString);

            ProjectCosts projectCost = new ProjectCosts();
            projectCost.setProject(project);
            projectCost.setExpenseType(expenseType);
            projectCost.setDescription(description);
            projectCost.setAmount(amount);
            projectCost.setDueDate(dueDate);

            projectCostsRepository.save(projectCost);
            System.out.println("Project cost added successfully.");
        } else {
            System.out.println("Project not found.");
        }
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
