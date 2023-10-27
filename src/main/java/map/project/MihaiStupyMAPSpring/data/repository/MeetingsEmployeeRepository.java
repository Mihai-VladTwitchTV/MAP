package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.MeetingsEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingsEmployeeRepository extends BaseRepository<MeetingsEmployee, Integer> {
    // Add specific repository methods here, if needed
}

