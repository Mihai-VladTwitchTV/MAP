package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Assignments")
public class Assignments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "assignmentName", nullable = false)
    private String assignmentName;

    // Constructors, getters, setters, and other fields as needed.
}
