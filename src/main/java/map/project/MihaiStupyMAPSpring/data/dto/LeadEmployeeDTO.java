package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.FullTimeEmployee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.LeadEmployee;
import map.project.MihaiStupyMAPSpring.service.DepartmentService;

public class LeadEmployeeDTO extends EmployeeDTO {
    private boolean isLeader;

    @Override
    public boolean isLeader() {
        return isLeader;
    }

    @Override
    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public LeadEmployee toLeadEmployee(DepartmentService departmentService) {
        LeadEmployee leadEmployee = new LeadEmployee();
        leadEmployee.setEmployeeID(this.getEmployeeID());
        leadEmployee.setType(this.getType());
        leadEmployee.setFirstName(this.getFirstName());
        leadEmployee.setLastName(this.getLastName());
        leadEmployee.setPhoneNumber(this.getPhoneNumber());
        leadEmployee.setEmailAddress(this.getEmailAddress());
        leadEmployee.setIsLeader(this.isLeader);
        leadEmployee.setDepartment(departmentService.findDepartmentById(this.getDepartmentId()));
        return leadEmployee;
    }

}
