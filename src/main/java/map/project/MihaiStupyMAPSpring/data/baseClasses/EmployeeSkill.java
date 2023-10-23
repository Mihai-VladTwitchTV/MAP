package map.project.MihaiStupyMAPSpring.data.baseClasses;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

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

    private int skillLevel;
}

@Embeddable
@Data
class EmployeeSkillId implements Serializable {
    private int employeeID;
    private int skillID;
}

