package map.project.MihaiStupyMAPSpring.data.baseClasses;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeSkillId implements Serializable {
    private int employeeID;
    private int skillID;

    public EmployeeSkillId() {
    }

    public EmployeeSkillId(int employeeID, int skillID) {
        this.employeeID = employeeID;
        this.skillID = skillID;
    }
}
