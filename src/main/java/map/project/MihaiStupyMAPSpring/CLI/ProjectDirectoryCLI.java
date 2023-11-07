package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@ShellComponent
public class ProjectDirectoryCLI {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @ShellMethod(key = "list-projects", value = "List all projects")
    public String listAllProjects() {
        eventPublisher.publishRepositoryMethodEvent(this);
        Iterable<Project> projects = projectRepository.findAll();
        StringBuilder result = new StringBuilder("List of Projects:\n");
        projects.forEach(project -> result.append(project.getProjectID()).append(": ").append(project.getProjectName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-project", value = "Add a new project")
    public String addProject(
            @ShellOption(value = "projectID", help = "Project ID") int projectID,
            @ShellOption(value = "clientID", help = "Client ID") int clientID,
            @ShellOption(value = "departmentID", help = "Department ID") int departmentID,
            @ShellOption(value = "projectName", help = "Project Name") String projectName,
            @ShellOption(value = "startDate", help = "Start Date (yyyy-MM-dd HH:mm:ss)") String startDateStr,
            @ShellOption(value = "endDate", help = "End Date (yyyy-MM-dd HH:mm:ss)") String endDateStr,
            @ShellOption(value = "status", help = "Status") String status,
            @ShellOption(value = "meetingType", help = "Meeting Type") String meetingType) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Client client = clientRepository.findById(clientID).orElse(null);
        Department department = departmentRepository.findById(departmentID);

        if (client != null && department != null) {
            Date startDate = parseDate(startDateStr);
            Date endDate = parseDate(endDateStr);

            Project project = new Project(projectID, client, department, projectName, startDate, endDate, status, meetingType, null, null, null);
            eventPublisher.publishRepositoryMethodEvent(this);
            projectRepository.save(project);

            return "Project added successfully.";
        } else {
            return "Client or Department not found.";
        }
    }

    @ShellMethod(key = "update-project", value = "Update a project")
    public String updateProject(
            @ShellOption(value = "projectID", help = "Project ID") int projectID,
            @ShellOption(value = "clientID", help = "Client ID") int clientID,
            @ShellOption(value = "departmentID", help = "Department ID") int departmentID,
            @ShellOption(value = "projectName", help = "Project Name") String projectName,
            @ShellOption(value = "startDate", help = "Start Date (yyyy-MM-dd HH:mm:ss)") String startDateStr,
            @ShellOption(value = "endDate", help = "End Date (yyyy-MM-dd HH:mm:ss)") String endDateStr,
            @ShellOption(value = "status", help = "Status") String status,
            @ShellOption(value = "meetingType", help = "Meeting Type") String meetingType) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Project project = projectRepository.findById(projectID).orElse(null);
        eventPublisher.publishRepositoryMethodEvent(this);
        Client client = clientRepository.findById(clientID).orElse(null);
        eventPublisher.publishRepositoryMethodEvent(this);
        Department department = departmentRepository.findById(departmentID);

        if (project != null && client != null && department != null) {
            project.setClient(client);
            project.setDepartment(department);
            project.setProjectName(projectName);

            Date startDate = parseDate(startDateStr);
            Date endDate = parseDate(endDateStr);

            project.setStartDate(startDate);
            project.setEndDate(endDate);
            project.setStatus(status);
            project.setMeetingType(meetingType);

            eventPublisher.publishRepositoryMethodEvent(this);
            projectRepository.save(project);
            return "Project updated successfully.";
        } else {
            return "Project, Client, or Department not found.";
        }
    }

    @ShellMethod(key = "delete-project", value = "Delete a project")
    public String deleteProject(@ShellOption(value = "projectID", help = "Project ID") int projectID) {
        eventPublisher.publishRepositoryMethodEvent(this);
        Project project = projectRepository.findById(projectID).orElse(null);

        if (project != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            projectRepository.delete(project);
            return "Project deleted successfully.";
        } else {
            return "Project not found.";
        }
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
