package map.project.MihaiStupyMAPSpring.data.baseClasses;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillID;

    private String skillName;
    private String description;
}

