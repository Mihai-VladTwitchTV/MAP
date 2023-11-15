package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@SpringBootApplication
@ShellComponent
public class DepartmentDirectoryCLI {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    public DepartmentDirectoryCLI(DepartmentRepository departmentRepository) {
    }

    @ShellMethod(key = "list-departments", value = "List all departments")
    public String listAllDepartments() {
        StringBuilder result = new StringBuilder("List of Departments:\n");
        Iterable<Department> departments = departmentRepository.findAll();
        eventPublisher.publishRepositoryMethodEvent(this);
        departments.forEach(department -> result.append(department.getDepartmentID()).append(": ").append(department.getSpecialization()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-department", value = "Add a new department")
    public String addDepartment(@ShellOption({"-id", "--departmentID"}) int departmentID, @ShellOption({"-max", "--maxEmployees"}) int maxEmployees, @ShellOption({"-spec", "--specialization"}) String specialization) {
        Department department = new Department(departmentID, maxEmployees, specialization);
        eventPublisher.publishRepositoryMethodEvent(this);
        departmentRepository.save(department);
        return "Department added successfully.";
    }

    @ShellMethod(key = "update-department", value = "Update a department")
    public String updateDepartment(@ShellOption({"-id", "--departmentID"}) int departmentID, @ShellOption({"-max", "--maxEmployees"}) int maxEmployees, @ShellOption({"-spec", "--specialization"}) String specialization) {
        eventPublisher.publishRepositoryMethodEvent(this);
        Department department = departmentRepository.findById(departmentID);
        if (department != null) {
            department.setMaxEmployees(maxEmployees);
            department.setSpecialization(specialization);
            eventPublisher.publishRepositoryMethodEvent(this);
            departmentRepository.save(department);
            return "Department with ID " + departmentID + " updated successfully.";
        } else {
            return "Department with ID " + departmentID + " not found.";
        }
    }


    @ShellMethod(key = "delete-department", value = "Delete a department")
    public String deleteDepartment(@ShellOption({"-id", "--departmentID"}) int departmentID) {
        Department department = departmentRepository.findById(departmentID);
        if (department != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            departmentRepository.delete(department);
            return "Department deleted successfully.";
        } else {
            return "Department not found.";
        }
    }
}
