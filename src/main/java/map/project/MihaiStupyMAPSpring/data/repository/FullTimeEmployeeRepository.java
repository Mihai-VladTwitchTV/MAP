package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.FullTimeEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface FullTimeEmployeeRepository extends BaseRepository<FullTimeEmployee, Integer> {
    // Add specific repository methods here, if needed
}

