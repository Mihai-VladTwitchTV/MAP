package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.service.*;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;


public class AssignmentsDTO {


    private int id;
    private String assignmentName;
    private int projectId; // Assuming we only need the project ID
    private Set<EmployeeDTO> employees;
    private ProjectDTO project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Assignments toAssignments(ProjectService projectService,
                                     DepartmentService departmentService,
                                     ClientService clientService,
                                     ProjectCostsService costsService,
                                     ProjectMilestonesService milestonesService,
                                     AssignmentsService assignmentsService) {
        Assignments assignments = new Assignments();
        assignments.setId(this.id);
        assignments.setAssignmentName(this.assignmentName);

        if (this.project != null) {
            // ProjectService, along with other required services, is passed to toProject method
            assignments.setProject(this.project.toProject(clientService, departmentService, assignmentsService, costsService, milestonesService));
        }

        if (this.employees != null) {
            // DepartmentService and AssignmentsService are passed to toEmployee method
            assignments.setEmployees(
                    this.employees.stream()
                            .map(employeeDTO -> employeeDTO.toEmployee(departmentService, assignmentsService))
                            .collect(Collectors.toSet())
            );
        }

        return assignments;
    }


}
