package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(name = "assignmentName", nullable = false)
    private String assignmentName;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "projectid")
    private Project project;


    @Setter
    @Getter
    @OneToMany(mappedBy = "assignments")
    private Set<Employee> employees;


}