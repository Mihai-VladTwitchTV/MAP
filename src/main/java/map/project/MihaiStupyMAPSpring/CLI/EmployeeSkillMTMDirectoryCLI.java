package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeSkillId;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeSkill;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.repository.SkillRepository;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@ShellComponent
public class EmployeeSkillMTMDirectoryCLI {

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @ShellMethod(key = "list-employee-skills", value = "List all employee-skill relationships")
    public String listAllEmployeeSkillRelationships() {
        Iterable<EmployeeSkill> employeeSkills = employeeSkillRepository.findAll();
        StringBuilder result = new StringBuilder("List of Employee-Skill Relationships:\n");
        employeeSkills.forEach(employeeSkill -> {
            Employee employee = employeeSkill.getEmployee();
            Skill skill = employeeSkill.getSkill();
            result.append("Employee ID: ").append(employee.getEmployeeID()).append(", Skill ID: ").append(skill.getSkillID()).append(", Skill Level: ").append(employeeSkill.getSkillLevel()).append("\n");
        });
        return result.toString();
    }

    @ShellMethod(key = "add-employee-skill", value = "Add a new employee-skill relationship")
    public String addEmployeeSkillRelationship(
            @ShellOption(value = "employeeID", help = "Employee ID") int employeeID,
            @ShellOption(value = "skillID", help = "Skill ID") int skillID,
            @ShellOption(value = "skillLevel", help = "Skill Level") int skillLevel) {

        Employee employee = employeeRepository.findById(employeeID).orElse(null);
        Skill skill = skillRepository.findById(skillID).orElse(null);

        if (employee == null || skill == null) {
            return "Employee or Skill not found. Unable to add the relationship.";
        }

        EmployeeSkill employeeSkill = EmployeeSkill.create(employee, skill, skillLevel);
        employeeSkillRepository.save(employeeSkill);



        return "Employee-Skill Relationship added successfully.";
    }

    @ShellMethod(key = "delete-employee-skill", value = "Delete an employee-skill relationship")
    public String deleteEmployeeSkillRelationship(
            @ShellOption(value = "employeeID", help = "Employee ID") int employeeID,
            @ShellOption(value = "skillID", help = "Skill ID") int skillID) {

        EmployeeSkillId employeeSkillId = new EmployeeSkillId(employeeID, skillID);
        EmployeeSkill employeeSkill = employeeSkillRepository.findById(employeeSkillId);

        if (employeeSkill != null) {
            employeeSkillRepository.delete(employeeSkill);
            return "Employee-Skill Relationship deleted successfully.";
        } else {
            return "Employee-Skill Relationship not found. Unable to delete the relationship.";
        }
    }

}
