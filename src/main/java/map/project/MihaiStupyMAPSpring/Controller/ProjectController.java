package map.project.MihaiStupyMAPSpring.Controller;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.dto.ProjectDTO;
import map.project.MihaiStupyMAPSpring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AssignmentsService assignmentsService;

    @Autowired
    private ProjectCostsService costsService;

    @Autowired
    private ProjectMilestonesService milestonesService;

    // Get all projects
    @GetMapping()
    public List<Project> listProjects() {
        return projectService.findAll();
    }

    // Get a project by ID
    @GetMapping("/{id}")
    public Project findProject(@PathVariable int id) {
        return projectService.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // Add a new project
    @PostMapping()
    public Project addProject(@RequestBody ProjectDTO request) {
        Project project = request.toProject(clientService, departmentService, assignmentsService, costsService, milestonesService);
        return projectService.save(project);
    }

    // Update a project
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable int id, @RequestBody ProjectDTO request) {
        Project projectToUpdate = projectService.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Updating the existing project with new values from the request
        // This assumes you have setters in your Project class
        projectToUpdate.setClient(clientService.findClientById(request.getClientId()));
        projectToUpdate.setDepartment(departmentService.findDepartmentById(request.getDepartmentId()));
        projectToUpdate.setProjectName(request.getProjectName());
        projectToUpdate.setStartDate(request.getStartDate());
        projectToUpdate.setEndDate(request.getEndDate());
        projectToUpdate.setStatus(request.getStatus());
        projectToUpdate.setMeetingType(request.getMeetingType());

        // Update assignments, costs, milestones as necessary
        // This part will depend on your implementation of the services and entities
        // Example:
        // projectToUpdate.setAssignments(assignmentsService.findAssignmentsByIds(request.getAssignmentIds()));

        return projectService.save(projectToUpdate);
    }


    // Delete a project
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id) {
        projectService.delete(id);
    }
}
