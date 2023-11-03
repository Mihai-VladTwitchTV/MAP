package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "EmployeeSkill")
public class EmployeeSkill {
    @EmbeddedId
    private EmployeeSkillId id;

    @ManyToOne
    @MapsId("employeeID")
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @ManyToOne
    @MapsId("skillID")
    @JoinColumn(name = "skillID")
    private Skill skill;

    @Getter
    @Setter
    @Column(name = "skillLevel")
    private int skillLevel;

    public static EmployeeSkill create(Employee employee, Skill skill, int skillLevel) {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        EmployeeSkillId id = new EmployeeSkillId();
        id.setEmployeeID(employee.getEmployeeID());
        id.setSkillID(skill.getSkillID());
        employeeSkill.setId(id);
        employeeSkill.setEmployee(employee);
        employeeSkill.setSkill(skill);
        employeeSkill.setSkillLevel(skillLevel);
        return employeeSkill;
    }
}
