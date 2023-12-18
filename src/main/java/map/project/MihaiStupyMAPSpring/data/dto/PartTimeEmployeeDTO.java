package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.PartTimeEmployee;
import map.project.MihaiStupyMAPSpring.service.DepartmentService;

public class PartTimeEmployeeDTO extends EmployeeDTO {
    private boolean isPartTime;

    @Override
    public boolean isPartTime() {
        return isPartTime;
    }

    @Override
    public void setPartTime(boolean partTime) {
        isPartTime = partTime;
    }
    public PartTimeEmployee toPartTimeEmployee(DepartmentService departmentService) {
        PartTimeEmployee partTimeEmployee = new PartTimeEmployee();
        partTimeEmployee.setEmployeeID(this.getEmployeeID());
        partTimeEmployee.setType(this.getType());
        partTimeEmployee.setFirstName(this.getFirstName());
        partTimeEmployee.setLastName(this.getLastName());
        partTimeEmployee.setPhoneNumber(this.getPhoneNumber());
        partTimeEmployee.setEmailAddress(this.getEmailAddress());
        partTimeEmployee.setIsPartTime(this.isPartTime);
        partTimeEmployee.setDepartment(departmentService.findDepartmentById(this.getDepartmentId()));
        return partTimeEmployee;
    }

}
