package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.*;
import map.project.MihaiStupyMAPSpring.service.*;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

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
        Client client = clientService.findById(this.clientId);
        project.setClient(client);
        project.setDepartment(departmentService.findDepartmentById(this.departmentId));
        project.setProjectName(this.projectName);
        project.setStartDate(this.startDate);
        project.setEndDate(this.endDate);
        project.setStatus(this.status);
        project.setMeetingType(this.meetingType);

        // Handle the associations with Assignments, Costs, and Milestones
        // Note: Assuming the services provide methods to find entities by IDs
        // Adjust the logic as per your business rules and service methods
        if (this.assignmentIds != null) {
            Set<Assignments> assignments = this.assignmentIds.stream()
                    .map(assignmentsService::findById)
                    .collect(Collectors.toSet());
            project.setAssignments(assignments);
        }
// Handling Costs
        if (this.costIds != null) {
            Set<ProjectCosts> costs = this.costIds.stream()
                    .map(costsService::findById)
                    .collect(Collectors.toSet());
            project.setCosts(costs);
        }

        // Handling Milestones
        if (this.milestoneIds != null) {
            Set<ProjectMilestones> milestones = this.milestoneIds.stream()
                    .map(milestonesService::findById)
                    .collect(Collectors.toSet());
            project.setMilestones(milestones);
        }

        return project;
    }
}
