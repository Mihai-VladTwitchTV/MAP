package map.project.MihaiStupyMAPSpring.CLI;


import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeSkill;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.repository.SkillRepository;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EmployeeSkillMTMDirectoryCLI implements CommandLineRunner {

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeSkillMTMDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Employee-Skill Relationship Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all employee-skill relationships");
            System.out.println("2. Add a new employee-skill relationship");
            System.out.println("3. Delete an employee-skill relationship");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllEmployeeSkillRelationships();
                    break;
                case 2:
                    addEmployeeSkillRelationship(scanner);
                    break;
                case 3:
                    deleteEmployeeSkillRelationship(scanner);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllEmployeeSkillRelationships() {
        Iterable<EmployeeSkill> employeeSkills = employeeSkillRepository.findAll();
        System.out.println("List of Employee-Skill Relationships:");
        employeeSkills.forEach(employeeSkill -> {
            Employee employee = employeeSkill.getEmployee();
            Skill skill = employeeSkill.getSkill();
            System.out.println("Employee ID: " + employee.getEmployeeID() + ", Skill ID: " + skill.getSkillID() + ", Skill Level: " + employeeSkill.getSkillLevel());
        });
    }

    private void addEmployeeSkillRelationship(Scanner scanner) {
        System.out.println("Enter employee-skill relationship details:");

        // Get employee and skill details from the user
        Employee employee = createEmployee(scanner);
        Skill skill = createSkill(scanner);

        System.out.print("Skill Level: ");
        int skillLevel = scanner.nextInt();

        // Create a new employee-skill relationship
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);
        employeeSkill.setSkillLevel(skillLevel);

        // Save the employee-skill relationship to the database
        employeeSkillRepository.save(employeeSkill);

        System.out.println("Employee-Skill Relationship added successfully.");
    }

    private void deleteEmployeeSkillRelationship(Scanner scanner) {
        System.out.println("Enter employee ID to delete the relationship:");
        int employeeID = scanner.nextInt();
        Employee employee = employeeRepository.findById(employeeID).orElse(null);

        System.out.println("Enter skill ID to delete the relationship:");
        int skillID = scanner.nextInt();
        Skill skill = skillRepository.findById(skillID).orElse(null);

        if (employee == null || skill == null) {
            System.out.println("Employee or Skill not found. Unable to delete the relationship.");
            return;
        }

        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);

        // Delete the employee-skill relationship from the database
        employeeSkillRepository.delete(employeeSkill);

        System.out.println("Employee-Skill Relationship deleted successfully.");
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

    private Skill createSkill(Scanner scanner) {
        System.out.print("Enter Skill ID: ");
        int skillID = scanner.nextInt();

        Skill skill = skillRepository.findById(skillID).orElse(null);

        if (skill == null) {
            System.out.println("Skill not found. Please add the skill first.");
        }

        return skill;
    }
}
