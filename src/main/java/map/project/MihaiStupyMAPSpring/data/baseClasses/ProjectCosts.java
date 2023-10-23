package map.project.MihaiStupyMAPSpring.data.baseClasses;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "ProjectCosts")
public class ProjectCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int costID;

    @ManyToOne
    @JoinColumn(name = "projectID")
    private Project project;

    private String expenseType;
    private String description;
    private BigDecimal amount;
    private Date dueDate;
}
