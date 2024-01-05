package map.project.MihaiStupyMAPSpring.Controller;

import map.project.MihaiStupyMAPSpring.data.baseClasses.ProjectCosts;
import map.project.MihaiStupyMAPSpring.data.baseClasses.ProjectMilestones;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import map.project.MihaiStupyMAPSpring.data.dto.ProjectCostsDTO;
import map.project.MihaiStupyMAPSpring.data.dto.ProjectMilestonesDTO;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectCostsRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectMilestonesRepository;
import map.project.MihaiStupyMAPSpring.service.ProjectCostsService;
import map.project.MihaiStupyMAPSpring.service.ProjectMilestonesService;
import map.project.MihaiStupyMAPSpring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-details")
public class ProjectCostsAndMilestonesController {

    @Autowired
    private ProjectCostsService projectCostsService;
    @Autowired
    private ProjectMilestonesService projectMilestonesService;
    @Autowired
    private ProjectCostsRepository projectCostsRepository;
    @Autowired
    private ProjectMilestonesRepository projectMilestonesRepository;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/costs")
    public List<ProjectCosts> getAllProjectCosts() {
        return projectCostsService.findAll();
    }
    @GetMapping("/milestones")
    public List<ProjectMilestones> getAllProjectMilestones() {
        return projectMilestonesService.findAll();
    }


    // POST: Add a new project cost
    @PostMapping("/costs")
    public ProjectCosts addProjectCost(@RequestBody ProjectCostsDTO projectCostsDTO) {
        ProjectCosts projectCosts = convertCostDTOToEntity(projectCostsDTO);
        return projectCostsService.save(projectCosts);
    }

    // GET: Get a project cost by ID
    @GetMapping("/costs/{id}")
    public ProjectCosts getProjectCost(@PathVariable int id) {
        ProjectCosts projectCost = projectCostsService.findById(id);
        if (projectCost == null) {
            throw new RuntimeException("Project cost not found");
        }
        return projectCost;
    }


    // PUT: Update a project cost
    @PutMapping("/costs/{id}")
    public ProjectCosts updateProjectCost(@PathVariable int id, @RequestBody ProjectCostsDTO projectCostsDTO) {
        ProjectCosts projectCosts = projectCostsService.findById(id);
        if (projectCosts == null) {
            throw new RuntimeException("Project cost not found");
        }
        updateProjectCostEntity(projectCosts, projectCostsDTO);
        return projectCostsService.save(projectCosts);
    }


    // DELETE: Delete a project cost
    @DeleteMapping("/costs/{id}")
    public void deleteProjectCost(@PathVariable int id) {
        projectCostsRepository.deleteById(id);
    }

    // POST: Add a new project milestone
    @PostMapping("/milestones")
    public ProjectMilestones addProjectMilestone(@RequestBody ProjectMilestonesDTO projectMilestonesDTO) {
        ProjectMilestones projectMilestone = convertMilestoneDTOToEntity(projectMilestonesDTO);
        return projectMilestonesService.save(projectMilestone);
    }

    // GET: Get a project milestone by ID
    @GetMapping("/milestones/{id}")
    public ProjectMilestones getProjectMilestone(@PathVariable int id) {
        ProjectMilestones projectMilestone = projectMilestonesService.findById(id);
        if (projectMilestone == null) {
            throw new RuntimeException("Project milestone not found");
        }
        return projectMilestone;
    }


    // PUT: Update a project milestone
    @PutMapping("/milestones/{id}")
    public ProjectMilestones updateProjectMilestone(@PathVariable int id, @RequestBody ProjectMilestonesDTO projectMilestonesDTO) {
        ProjectMilestones projectMilestone = projectMilestonesService.findById(id);
        if (projectMilestone == null) {
            throw new RuntimeException("Project milestone not found");
        }
        updateProjectMilestoneEntity(projectMilestone, projectMilestonesDTO);
        return projectMilestonesService.save(projectMilestone);
    }


    // DELETE: Delete a project milestone
    @DeleteMapping("/milestones/{id}")
    public void deleteProjectMilestone(@PathVariable int id) {
        projectMilestonesRepository.deleteById(id);
    }

    // Utility methods for ProjectMilestones
    private ProjectMilestones convertMilestoneDTOToEntity(ProjectMilestonesDTO dto) {
        ProjectMilestones projectMilestone = new ProjectMilestones();
        projectMilestone.setMilestoneID(dto.getMilestoneID());
        projectMilestone.setMilestoneName(dto.getMilestoneName());
        projectMilestone.setDescription(dto.getDescription());
        // Set the project for this milestone, if needed
        projectMilestone.setProject(projectService.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found for ID: " + dto.getProjectId())));

        return projectMilestone;
    }

    private void updateProjectMilestoneEntity(ProjectMilestones projectMilestone, ProjectMilestonesDTO dto) {
        projectMilestone.setMilestoneName(dto.getMilestoneName());
        projectMilestone.setDescription(dto.getDescription());
        // Update the project for this milestone, if needed
        projectMilestone.setProject(projectService.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found for ID: " + dto.getProjectId())));
    }

    private ProjectCosts convertCostDTOToEntity(ProjectCostsDTO dto) {
        // Conversion logic for ProjectCostsDTO to ProjectCosts
        ProjectCosts projectCosts = new ProjectCosts();
        projectCosts.setCostID(dto.getCostID());
        projectCosts.setExpenseType(dto.getExpenseType());
        projectCosts.setDescription(dto.getDescription());
        projectCosts.setAmount(dto.getAmount());
        projectCosts.setDueDate(dto.getDueDate());
        // Set the project for this cost, if needed
        projectCosts.setProject(projectService.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found for ID: " + dto.getProjectId())));
        return projectCosts;
    }

    private void updateProjectCostEntity(ProjectCosts projectCosts, ProjectCostsDTO dto) {
        // Update properties of projectCosts with values from dto
        projectCosts.setExpenseType(dto.getExpenseType());
        projectCosts.setDescription(dto.getDescription());
        projectCosts.setAmount(dto.getAmount());
        projectCosts.setDueDate(dto.getDueDate());
        // Update the project for this cost, if needed
        projectCosts.setProject(projectService.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found for ID: " + dto.getProjectId())));
    }

}
