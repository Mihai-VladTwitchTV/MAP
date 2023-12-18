package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.service.*;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class ProjectDTO {
    private int projectID;
    private int clientId; // Assuming just the ID is needed
    private int departmentId; // Assuming just the ID is needed
    private String projectName;
    private Date startDate;
    private Date endDate;
    private String status;
    private String meetingType;
    private Set<Integer> assignmentIds; // Assuming just the IDs are needed
    private Set<Integer> costIds; // Assuming just the IDs are needed
    private Set<Integer> milestoneIds; // Assuming just the IDs are needed

    private int assignmentID;
    private int costID;
    private int milestoneID;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getCostID() {
        return costID;
    }

    public void setCostID(int costID) {
        this.costID = costID;
    }

    public int getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(int milestoneID) {
        this.milestoneID = milestoneID;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public Set<Integer> getAssignmentIds() {
        return assignmentIds;
    }

    public void setAssignmentIds(Set<Integer> assignmentIds) {
        this.assignmentIds = assignmentIds;
    }

    public Set<Integer> getCostIds() {
        return costIds;
    }

    public void setCostIds(Set<Integer> costIds) {
        this.costIds = costIds;
    }

    public Set<Integer> getMilestoneIds() {
        return milestoneIds;
    }

    public void setMilestoneIds(Set<Integer> milestoneIds) {
        this.milestoneIds = milestoneIds;
    }

    public Project toProject(ClientService clientService, DepartmentService departmentService,
                             AssignmentsService assignmentsService, ProjectCostsService costsService,
                             ProjectMilestonesService milestonesService) {

        Project project = new Project();
        project.setProjectID(this.projectID);
        project.setClient(clientService.findById(this.clientId));
        project.setDepartment(departmentService.findDepartmentById(this.departmentId));
        project.setProjectName(this.projectName);
        project.setStartDate(this.startDate);
        project.setEndDate(this.endDate);
        project.setStatus(this.status);
        project.setMeetingType(this.meetingType);

        // Assuming services provide methods to find entities by IDs
        project.setAssignments(Collections.singleton(assignmentsService.findById(this.assignmentID)));
        project.setCosts(Collections.singleton(costsService.findById(this.costID)));
        project.setMilestones(Collections.singleton(milestonesService.findById(this.milestoneID)));

        return project;
    }
}
