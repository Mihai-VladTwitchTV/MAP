package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.FullTimeEmployee;
import map.project.MihaiStupyMAPSpring.service.DepartmentService;

public class FullTimeEmployeeDTO extends EmployeeDTO {
    private boolean isFullTime;

    @Override
    public boolean isFullTime() {
        return isFullTime;
    }

    @Override
    public void setFullTime(boolean fullTime) {
        isFullTime = fullTime;
    }
    public FullTimeEmployee toFullTimeEmployee(DepartmentService departmentService) {
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
        fullTimeEmployee.setEmployeeID(this.getEmployeeID());
        fullTimeEmployee.setType(this.getType());
        fullTimeEmployee.setFirstName(this.getFirstName());
        fullTimeEmployee.setLastName(this.getLastName());
        fullTimeEmployee.setPhoneNumber(this.getPhoneNumber());
        fullTimeEmployee.setEmailAddress(this.getEmailAddress());
        fullTimeEmployee.setIsFullTime(this.isFullTime);
        fullTimeEmployee.setDepartment(departmentService.findDepartmentById(this.getDepartmentId()));
        return fullTimeEmployee;
    }

}
