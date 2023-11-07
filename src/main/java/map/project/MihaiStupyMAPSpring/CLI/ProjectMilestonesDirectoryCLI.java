package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.baseClasses.ProjectMilestones;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectMilestonesRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@ShellComponent
public class ProjectMilestonesDirectoryCLI {

    @Autowired
    private ProjectMilestonesRepository projectMilestonesRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @ShellMethod(key = "list-project-milestones", value = "List all project milestones")
    public String listAllProjectMilestones() {
        eventPublisher.publishRepositoryMethodEvent(this);
        Iterable<ProjectMilestones> projectMilestones = projectMilestonesRepository.findAll();
        StringBuilder result = new StringBuilder("List of Project Milestones:\n");
        projectMilestones.forEach(milestone -> result.append(milestone.getMilestoneID()).append(": ").append(milestone.getMilestoneName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-project-milestone", value = "Add a new project milestone")
    public String addProjectMilestone(
            @ShellOption(value = "milestoneID", help = "Milestone ID") int milestoneID,
            @ShellOption(value = "projectID", help = "Project ID") int projectID,
            @ShellOption(value = "milestoneName", help = "Milestone Name") String milestoneName,
            @ShellOption(value = "description", help = "Description") String description) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Project project = projectRepository.findById(projectID).orElse(null);

        if (project != null) {
            ProjectMilestones milestone = new ProjectMilestones(milestoneID, project, milestoneName, description);
            eventPublisher.publishRepositoryMethodEvent(this);
            projectMilestonesRepository.save(milestone);
            return "Project milestone added successfully.";
        } else {
            return "Project not found. Please add the project first.";
        }
    }

    @ShellMethod(key = "update-project-milestone", value = "Update a project milestone")
    public String updateProjectMilestone(
            @ShellOption(value = "milestoneID", help = "Milestone ID") int milestoneID,
            @ShellOption(value = "projectID", help = "Project ID") int projectID,
            @ShellOption(value = "milestoneName", help = "Milestone Name") String milestoneName,
            @ShellOption(value = "description", help = "Description") String description) {

        eventPublisher.publishRepositoryMethodEvent(this);
        ProjectMilestones milestone = projectMilestonesRepository.findById(milestoneID).orElse(null);
        eventPublisher.publishRepositoryMethodEvent(this);
        Project project = projectRepository.findById(projectID).orElse(null);

        if (milestone != null && project != null) {
            milestone.setProject(project);
            milestone.setMilestoneName(milestoneName);
            milestone.setDescription(description);
            eventPublisher.publishRepositoryMethodEvent(this);
            projectMilestonesRepository.save(milestone);
            return "Project milestone updated successfully.";
        } else {
            return "Project milestone or Project not found.";
        }
    }

    @ShellMethod(key = "delete-project-milestone", value = "Delete a project milestone")
    public String deleteProjectMilestone(@ShellOption(value = "milestoneID", help = "Milestone ID") int milestoneID) {
        eventPublisher.publishRepositoryMethodEvent(this);
        ProjectMilestones milestone = projectMilestonesRepository.findById(milestoneID).orElse(null);

        if (milestone != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            projectMilestonesRepository.delete(milestone);
            return "Project milestone deleted successfully.";
        } else {
            return "Project milestone not found.";
        }
    }
}
