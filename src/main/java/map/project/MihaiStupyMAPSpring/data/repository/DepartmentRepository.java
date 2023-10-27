package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer> {
    // Add specific repository methods here, if needed
}

