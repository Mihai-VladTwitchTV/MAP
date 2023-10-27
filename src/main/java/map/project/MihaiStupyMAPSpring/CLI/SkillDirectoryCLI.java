package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import map.project.MihaiStupyMAPSpring.data.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SkillDirectoryCLI implements CommandLineRunner {

    @Autowired
    private SkillRepository skillRepository;

    public static void main(String[] args) {
        SpringApplication.run(SkillDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Skill Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all skills");
            System.out.println("2. Add a new skill");
            System. out.println("3. Update a skill");
            System.out.println("4. Delete a skill");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllSkills();
                    break;
                case 2:
                    addSkill(scanner);
                    break;
                case 3:
                    updateSkill(scanner);
                    break;
                case 4:
                    deleteSkill(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllSkills() {
        Iterable<Skill> skills = skillRepository.findAll();
        System.out.println("List of Skills:");
        skills.forEach(skill -> System.out.println(skill.getSkillID() + ": " + skill.getSkillName()));
    }

    private void addSkill(Scanner scanner) {
        System.out.println("Enter skill details:");

        // Get skill details from the user
        System.out.print("Skill ID: ");
        int skillID = scanner.nextInt();

        System.out.print("Skill Name: ");
        String skillName = scanner.next();

        System.out.print("Description: ");
        String description = scanner.next();

        // Create a new skill
        Skill skill = new Skill();
        skill.setSkillID(skillID);
        skill.setSkillName(skillName);
        skill.setDescription(description);

        // Save the skill to the database
        skillRepository.save(skill);

        System.out.println("Skill added successfully.");
    }

    private void updateSkill(Scanner scanner) {
        System.out.print("Enter skill ID to update: ");
        int skillID = scanner.nextInt();
        Skill skill = skillRepository.findById(skillID).orElse(null);

        if (skill == null) {
            System.out.println("Skill not found.");
            return;
        }

        System.out.println("Enter new skill details:");

        System.out.print("Skill Name: ");
        String skillName = scanner.next();
        skill.setSkillName(skillName);

        System.out.print("Description: ");
        String description = scanner.next();
        skill.setDescription(description);

        // Save the updated skill to the database
        skillRepository.save(skill);

        System.out.println("Skill updated successfully.");
    }

    private void deleteSkill(Scanner scanner) {
        System.out.print("Enter skill ID to delete: ");
        int skillID = scanner.nextInt();
        Skill skill = skillRepository.findById(skillID).orElse(null);

        if (skill == null) {
            System.out.println("Skill not found.");
            return;
        }

        // Delete the skill from the database
        skillRepository.delete(skill);

        System.out.println("Skill deleted successfully.");
    }
}
