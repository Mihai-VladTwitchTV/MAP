package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Assignments")
public class Assignments {
    public Assignments(int id, String assignmentName) {
        this.id = id;
        this.assignmentName = assignmentName;
    }

    public Assignments() {
    }

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Setter
    @Getter
    @Column(name = "assignmentName", nullable = false)
    private String assignmentName;

    // Constructors, getters, setters, and other fields as needed.
}
