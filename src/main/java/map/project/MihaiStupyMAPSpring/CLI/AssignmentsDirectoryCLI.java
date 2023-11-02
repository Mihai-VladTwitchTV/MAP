package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@ShellComponent
public class AssignmentsDirectoryCLI {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @ShellMethod(key = "list-assignments", value = "List all assignments")
    public String listAllAssignments() {
        StringBuilder result = new StringBuilder("List of Assignments:\n");
        Iterable<Assignments> assignments = assignmentsRepository.findAll();
        assignments.forEach(assignment -> result.append(assignment.getId()).append(": ").append(assignment.getAssignmentName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-assignment", value = "Add a new assignment")
    public String addAssignment(@ShellOption({"-id", "--assignmentId"}) int assignmentId, @ShellOption({"-name", "--assignmentName"}) String assignmentName) {
        Assignments assignment = new Assignments(assignmentId, assignmentName);
        assignmentsRepository.save(assignment);
        return "Assignment added successfully.";
    }

    @ShellMethod(key = "update-assignment", value = "Update an assignment")
    public String updateAssignment(@ShellOption({"-id", "--assignmentId"}) int assignmentId, @ShellOption({"-name", "--assignmentName"}) String assignmentName) {
        Assignments assignment = assignmentsRepository.findById(assignmentId).orElse(null);
        if (assignment != null) {
            assignment.setAssignmentName(assignmentName);
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
            assignmentsRepository.delete(assignment);
            return "Assignment deleted successfully.";
        } else {
            return "Assignment not found.";
        }
    }
}
