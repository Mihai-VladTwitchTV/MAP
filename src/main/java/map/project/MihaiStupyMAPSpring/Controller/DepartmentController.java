package map.project.MihaiStupyMAPSpring.Controller;

import java.util.List;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.dto.DepartmentDTO;
import map.project.MihaiStupyMAPSpring.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Get all departments
    @GetMapping()
    public List<Department> listDepartments() {
        return departmentService.findAllDepartments();
    }

    // Get a department by ID
    @GetMapping("/{id}")
    public Department findDepartment(@PathVariable int id) {
        return departmentService.findDepartmentById(id);
    }


    // Add a new department
    @PostMapping()
    public Department addDepartment(@RequestBody DepartmentDTO request) {
        Department department = new Department();
        department.setMaxEmployees(request.getMaxEmployees());
        department.setSpecialization(request.getSpecialization());
        return departmentService.save(department);
    }

    // Update a department
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable int id, @RequestBody DepartmentDTO request) {
        return departmentService.updateDepartment(id, request.toDepartment());
    }

    // Delete a department
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartmentById(id);
    }
}
