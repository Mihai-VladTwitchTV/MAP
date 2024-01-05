package map.project.MihaiStupyMAPSpring.Controller;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.dto.EmployeeDTO;
import map.project.MihaiStupyMAPSpring.service.AssignmentsService;
import map.project.MihaiStupyMAPSpring.service.EmployeeService;
import map.project.MihaiStupyMAPSpring.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AssignmentsService assignmentsService;

    @GetMapping()
    public List<Employee> listEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findEmployee(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody EmployeeDTO request) {
        Employee employee = request.toEmployee(departmentService, assignmentsService);
        return employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.delete(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO request) {
        Employee existingEmployee = employeeService.getById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Assuming the EmployeeDTO has a method to update the existing employee entity
        request.updateEmployee(existingEmployee, departmentService, assignmentsService);

        return employeeService.save(existingEmployee);
    }

}
