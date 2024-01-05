package map.project.MihaiStupyMAPSpring.data.baseClasses;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


@Entity
@Data
@Table(name = "ProjectCosts")
public class ProjectCosts {
    public ProjectCosts() {
    }

    public ProjectCosts(int costID, Project project, String expenseType, String description, BigDecimal amount, Date dueDate) {
        this.costID = costID;
        this.project = project;
        this.expenseType = expenseType;
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
    }

    @Getter
    @Setter
    @Id
    private int costID;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "projectid")
    private Project project;

    @Getter
    @Setter
    @Column(name = "expenseType")
    private String expenseType;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "amount")
    private BigDecimal amount;

    @Getter
    @Setter
    @Column(name = "dueDate")
    private Date dueDate;


//    @Override
//    public int hashCode() {
//        return Objects.hash(costID);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        ProjectCosts other = (ProjectCosts) obj;
//        return costID == other.costID;
//    }


}
