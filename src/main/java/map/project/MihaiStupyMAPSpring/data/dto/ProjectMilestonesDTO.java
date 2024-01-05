package map.project.MihaiStupyMAPSpring.data.dto;

public class ProjectMilestonesDTO {
    private int milestoneID;
    private int projectId; // Assuming you only need the project ID
    private String milestoneName;
    private String description;

    public int getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(int milestoneID) {
        this.milestoneID = milestoneID;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Add any additional methods needed for conversion to/from ProjectMilestones entity
}
