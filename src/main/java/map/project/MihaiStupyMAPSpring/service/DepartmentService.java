package map.project.MihaiStupyMAPSpring.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department findDepartmentById(int departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Transactional
    public void deleteDepartmentById(int departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Transactional
    public Department updateDepartment(int departmentId, Department updatedDepartment) {
        Department existingDepartment = departmentRepository.findById(departmentId);

        if (existingDepartment != null) {
            // Update the existing department with the new values
            existingDepartment.setMaxEmployees(updatedDepartment.getMaxEmployees());
            existingDepartment.setSpecialization(updatedDepartment.getSpecialization());


            // Save the updated department
            return departmentRepository.save(existingDepartment);
        }

        // Handle the case where the department with the given ID is not found
        return null;
    }

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

}
