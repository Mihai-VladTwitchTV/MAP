package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.service.ProjectService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;



public class AssignmentsDTO {


    private int id;
    private String assignmentName;
    private int projectId; // Assuming we only need the project ID

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

    public Assignments toAssignments(ProjectService projectService) {
        Assignments assignments = new Assignments();
        assignments.setId(this.id);
        assignments.setAssignmentName(this.assignmentName);

        projectService.findById(this.projectId).ifPresent(assignments::setProject);

        return assignments;
    }


}
