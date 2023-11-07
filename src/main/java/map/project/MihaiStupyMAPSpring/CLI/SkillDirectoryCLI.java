package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@ShellComponent
public class SkillDirectoryCLI {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @ShellMethod(key = "list-skills", value = "List all skills")
    public String listAllSkills() {
        eventPublisher.publishRepositoryMethodEvent(this);
        Iterable<Skill> skills = skillRepository.findAll();
        StringBuilder result = new StringBuilder("List of Skills:\n");
        skills.forEach(skill -> result.append(skill.getSkillID()).append(": ").append(skill.getSkillName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-skill", value = "Add a new skill")
    public String addSkill(
            @ShellOption(value = "skillID", help = "Skill ID") int skillID,
            @ShellOption(value = "skillName", help = "Skill Name") String skillName,
            @ShellOption(value = "description", help = "Description") String description) {

        Skill skill = new Skill();
        skill.setSkillID(skillID);
        skill.setSkillName(skillName);
        skill.setDescription(description);

        eventPublisher.publishRepositoryMethodEvent(this);
        skillRepository.save(skill);

        return "Skill added successfully.";
    }

    @ShellMethod(key = "update-skill", value = "Update a skill")
    public String updateSkill(
            @ShellOption(value = "skillID", help = "Skill ID") int skillID,
            @ShellOption(value = "skillName", help = "Skill Name") String skillName,
            @ShellOption(value = "description", help = "Description") String description) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Skill skill = skillRepository.findById(skillID).orElse(null);

        if (skill != null) {
            skill.setSkillName(skillName);
            skill.setDescription(description);

            eventPublisher.publishRepositoryMethodEvent(this);
            skillRepository.save(skill);
            return "Skill updated successfully.";
        } else {
            return "Skill not found.";
        }
    }

    @ShellMethod(key = "delete-skill", value = "Delete a skill")
    public String deleteSkill(@ShellOption(value = "skillID", help = "Skill ID") int skillID) {
        eventPublisher.publishRepositoryMethodEvent(this);
        Skill skill = skillRepository.findById(skillID).orElse(null);

        if (skill != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            skillRepository.delete(skill);
            return "Skill deleted successfully.";
        } else {
            return "Skill not found.";
        }
    }
}
