package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.hibernate.sql.ast.tree.update.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@ShellComponent
@Component
public class AssignmentsDirectoryCLI {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @Autowired
    ProjectRepository projectRepository;

//    @Autowired
//    private RepositoryMethodEventPublisher eventPublisher;

    @Override
    public String toString() {
        return "Assigment CLI";
    }

    @Autowired
    public AssignmentsDirectoryCLI(AssignmentsRepository assignmentsRepository) {
        this.assignmentsRepository = assignmentsRepository;
    }

    public int countAllAssignments() {
        List<Assignments> assignments = assignmentsRepository.findAll();
        return assignments.size();
    }

    @ShellMethod(key = "list-assignments", value = "List all assignments")
    public String listAllAssignments() {
        StringBuilder result = new StringBuilder("List of Assignments:\n");
        Iterable<Assignments> assignments = assignmentsRepository.findAll();
//        eventPublisher.publishRepositoryMethodEvent(this);
        assignments.forEach(assignment -> result.append(assignment.getId()).append(": ").append(assignment.getAssignmentName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-assignment", value = "Add a new assignment")
    public String addAssignment(
            @ShellOption({"-id", "--assignmentId"}) int assignmentId,
            @ShellOption({"-name", "--assignmentName"}) String assignmentName,
            @ShellOption({"-projectid", "--projectid"}) int projectid
    ) {
        // Retrieve the Project instance based on projectid
        Optional<Project> optionalProject = projectRepository.findById(projectid);

        if (optionalProject.isPresent()) {
            // Project instance exists, proceed with creating the Assignments instance
            Project project = optionalProject.get();

            Assignments assignment = new Assignments(assignmentId, assignmentName);
            assignment.setProject(project);

            assignmentsRepository.save(assignment);

            return "Assignment added successfully.";
        } else {
            return "Error: Project with projectid " + projectid + " not found.";
        }
    }


    @ShellMethod(key = "update-assignment", value = "Update an assignment")
    public String updateAssignment(@ShellOption({"-id", "--assignmentId"}) int assignmentId, @ShellOption({"-name", "--assignmentName"}) String assignmentName) {
//        eventPublisher.publishRepositoryMethodEvent(this);
        Assignments assignment = assignmentsRepository.findById(assignmentId).orElse(null);
        if (assignment != null) {
            assignment.setAssignmentName(assignmentName);
//            eventPublisher.publishRepositoryMethodEvent(this);
            assignmentsRepository.save(assignment);
            return "Assignment updated successfully.";
        } else {
            return "Assignment not found.";
        }
    }

    @ShellMethod(key = "delete-assignment", value = "Delete an assignment")
    public String deleteAssignment(@ShellOption({"-id", "--assignmentId"}) int assignmentId) {
        Assignments assignment = assignmentsRepository.findById(assignmentId).orElse(null);
        if (assignment != null) {
//            eventPublisher.publishRepositoryMethodEvent(this);
            assignmentsRepository.delete(assignment);
            return "Assignment deleted successfully.";
        } else {
            return "Assignment not found.";
        }
    }
}
