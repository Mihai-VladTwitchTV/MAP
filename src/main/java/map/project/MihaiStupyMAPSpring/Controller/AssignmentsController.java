package map.project.MihaiStupyMAPSpring.Controller;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.dto.AssignmentsDTO;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import map.project.MihaiStupyMAPSpring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentsController {

    @Autowired
    private AssignmentsService assignmentsService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProjectCostsService costsService;
    @Autowired
    private ProjectMilestonesService milestonesService;
    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @GetMapping()
    public List<Assignments> listAssignments() {
        return assignmentsService.findAll();
    }

    @GetMapping("/{id}")
    public Assignments findAssignment(@PathVariable int id) {
        return assignmentsService.findById(id);
    }

    @PostMapping()
    public Assignments addAssignment(@RequestBody AssignmentsDTO request) {
        return assignmentsService.save(request.toAssignments(projectService, departmentService, clientService, costsService, milestonesService, assignmentsService));
    }

    @PutMapping("/{id}")
    public Assignments updateAssignment(@PathVariable int id, @RequestBody AssignmentsDTO request) {
        return assignmentsService.update(id, request.toAssignments(projectService, departmentService, clientService, costsService, milestonesService, assignmentsService));
    }

    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable int id) {
        assignmentsRepository.deleteById(id);
    }

    // Additional methods for update and delete
}
