package map.project.MihaiStupyMAPSpring.Controller;

import map.project.MihaiStupyMAPSpring.data.baseClasses.*;
import map.project.MihaiStupyMAPSpring.data.dto.ProjectDTO;
import map.project.MihaiStupyMAPSpring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        projectToUpdate.setClient(clientService.findClientById(request.getClientId()));
        Department department = departmentService.findDepartmentById(request.getDepartmentId());
        if (department == null) {
            throw new RuntimeException("Department not found for ID: " + request.getDepartmentId());
        }
        projectToUpdate.setDepartment(department);
        projectToUpdate.setProjectName(request.getProjectName());
        projectToUpdate.setStartDate(request.getStartDate());
        projectToUpdate.setEndDate(request.getEndDate());
        projectToUpdate.setStatus(request.getStatus());
        projectToUpdate.setMeetingType(request.getMeetingType());

        // Update assignments
        if (request.getAssignmentIds() != null) {
            // Clear existing assignments and add new ones
            projectToUpdate.getAssignments().clear();
            request.getAssignmentIds().forEach(assignmentId -> {
                Assignments assignment = assignmentsService.findById(assignmentId);
                if (assignment != null) {
                    projectToUpdate.addAssignment(assignment);  // addAssignment method should handle bi-directional setting
                }
            });
        }

        // Update costs
        if (request.getCostIds() != null && !request.getCostIds().isEmpty()) {
            Set<ProjectCosts> updatedCosts = request.getCostIds().stream()
                    .map(costsService::findById)
                    .collect(Collectors.toSet());
            projectToUpdate.setCosts(updatedCosts);
        }

        // Update milestones
        if (request.getMilestoneIds() != null && !request.getMilestoneIds().isEmpty()) {
            Set<ProjectMilestones> updatedMilestones = request.getMilestoneIds().stream()
                    .map(milestonesService::findById)
                    .collect(Collectors.toSet());
            projectToUpdate.setMilestones(updatedMilestones);
        }

        return projectService.save(projectToUpdate);
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id) {
        projectService.delete(id);
    }
}
