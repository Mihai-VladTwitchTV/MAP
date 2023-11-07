package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeProject;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeProjectRepository;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@ShellComponent
public class EmployeeProjectMTMDirectoryCLI {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @ShellMethod(key = "list-employee-projects", value = "List all employee-project relationships")
    public String listAllEmployeeProjectRelationships() {
        eventPublisher.publishRepositoryMethodEvent(this);
        Iterable<EmployeeProject> employeeProjects = employeeProjectRepository.findAll();
        StringBuilder result = new StringBuilder("List of Employee-Project Relationships:\n");
        employeeProjects.forEach(employeeProject -> {
            Employee employee = employeeProject.getEmployee();
            Project project = employeeProject.getProject();
            result.append("Employee ID: ").append(employee.getEmployeeID()).append(", Project ID: ").append(project.getProjectID()).append("\n");
        });
        return result.toString();
    }

    @ShellMethod(key = "add-employee-project", value = "Add a new employee-project relationship")
    public String addEmployeeProjectRelationship(
            @ShellOption(value = "employeeID", help = "Employee ID") int employeeID,
            @ShellOption(value = "projectID", help = "Project ID") int projectID) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Employee employee = employeeRepository.findById(employeeID).orElse(null);
        eventPublisher.publishRepositoryMethodEvent(this);
        Project project = projectRepository.findById(projectID).orElse(null);

        if (employee == null || project == null) {
            return "Employee or Project not found. Unable to add the relationship.";
        }
        eventPublisher.publishRepositoryMethodEvent(this);
        EmployeeProject employeeProject = new EmployeeProject(employee, project, null);
        employeeProjectRepository.save(employeeProject);

        return "Employee-Project Relationship added successfully.";
    }

    @ShellMethod(key = "delete-employee-project", value = "Delete an employee-project relationship")
    public String deleteEmployeeProjectRelationship(
            @ShellOption(value = "employeeID", help = "Employee ID") int employeeID,
            @ShellOption(value = "projectID", help = "Project ID") int projectID) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Employee employee = employeeRepository.findById(employeeID).orElse(null);
        eventPublisher.publishRepositoryMethodEvent(this);
        Project project = projectRepository.findById(projectID).orElse(null);

        if (employee == null || project == null) {
            return "Employee or Project not found. Unable to delete the relationship.";
        }
        eventPublisher.publishRepositoryMethodEvent(this);
        EmployeeProject employeeProject = new EmployeeProject(employee, project, null);
        employeeProjectRepository.delete(employeeProject);

        return "Employee-Project Relationship deleted successfully.";
    }
}
