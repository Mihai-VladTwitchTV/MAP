package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;

public class SkillDTO {
    private String skillName;
    private int skillID;
    private String description;

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    // Getter for skillName
    public String getSkillName() {
        return skillName;
    }

    // Setter for skillName
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    public Skill toSkill() {
        Skill skill = new Skill();
        skill.setSkillName(this.skillName);
        skill.setDescription(this.description);
        skill.setSkillID(this.skillID);
        return skill;
    }

}