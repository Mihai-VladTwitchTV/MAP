package map.project.MihaiStupyMAPSpring.data.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectCostsDTO {
    private int costID;
    private int projectId; // Assuming you only need the project ID
    private String expenseType;
    private String description;
    private BigDecimal amount;
    private Date dueDate;

    public int getCostID() {
        return costID;
    }

    public void setCostID(int costID) {
        this.costID = costID;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Add any additional methods needed for conversion to/from ProjectCosts entity
}
