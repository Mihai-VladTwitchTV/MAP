package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // Add specific repository methods here, if needed

    Department findById(int departmentID);

    Department createDepartment(Department department);

    Department updateDepartment(int departmentId, Department updatedDepartment);
}

