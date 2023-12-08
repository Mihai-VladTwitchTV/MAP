package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.baseClasses.ProjectCosts;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectCostsRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@ShellComponent
public class ProjectCostsDirectoryCLI {

    @Autowired
    private ProjectCostsRepository projectCostsRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @Override
    public String toString() {
        return "Project Cost CLI";
    }

    @ShellMethod(key = "list-project-costs", value = "List project costs")
    public String listProjectCosts() {
        eventPublisher.publishRepositoryMethodEvent(this);
        Iterable<ProjectCosts> projectCosts = projectCostsRepository.findAll();
        StringBuilder result = new StringBuilder("List of Project Costs:\n");
        projectCosts.forEach(projectCost -> result.append("Cost ID: ").append(projectCost.getCostID())
                .append(", Project ID: ").append(projectCost.getProject().getProjectID())
                .append(", Expense Type: ").append(projectCost.getExpenseType())
                .append(", Description: ").append(projectCost.getDescription())
                .append(", Amount: ").append(projectCost.getAmount())
                .append(", Due Date: ").append(formatDate(projectCost.getDueDate()))
                .append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-project-cost", value = "Add project cost")
    public String addProjectCost(
            @ShellOption(value = "projectID", help = "Project ID") int projectID,
            @ShellOption(value = "expenseType", help = "Expense Type") String expenseType,
            @ShellOption(value = "description", help = "Description") String description,
            @ShellOption(value = "amount", help = "Amount") BigDecimal amount,
            @ShellOption(value = "dueDate", help = "Due Date (yyyy-MM-dd)") String dueDateString) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Project project = projectRepository.findById(projectID).orElse(null);

        if (project != null) {
            Date dueDate = parseDate(dueDateString);

            ProjectCosts projectCost = new ProjectCosts();
            projectCost.setProject(project);
            projectCost.setExpenseType(expenseType);
            projectCost.setDescription(description);
            projectCost.setAmount(amount);
            projectCost.setDueDate(dueDate);

            eventPublisher.publishRepositoryMethodEvent(this);
            projectCostsRepository.save(projectCost);

            return "Project cost added successfully.";
        } else {
            return "Project not found.";
        }
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
