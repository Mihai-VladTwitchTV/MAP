package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeProject;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectRepository extends BaseRepository<EmployeeProject, Integer> {
    // Add specific repository methods here, if needed
}

