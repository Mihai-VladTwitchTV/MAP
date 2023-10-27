package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Meetings;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingsRepository extends BaseRepository<Meetings, Integer> {
    // Add specific repository methods here, if needed
}
