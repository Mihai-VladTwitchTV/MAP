package map.project.MihaiStupyMAPSpring.data.baseClasses;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Skill")
public class Skill {
    @Getter
    @Setter
    @Id
    private int skillID;

    @Getter
    @Setter
    @Column(name = "skillName")
    private String skillName;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;
}

