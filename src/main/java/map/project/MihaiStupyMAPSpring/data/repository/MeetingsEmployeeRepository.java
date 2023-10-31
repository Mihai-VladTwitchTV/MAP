package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Meetings;
import map.project.MihaiStupyMAPSpring.data.baseClasses.MeetingsEmployee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.MeetingsEmployeeId;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingsEmployeeRepository extends BaseRepository<MeetingsEmployee, Integer> {
    Iterable<MeetingsEmployee> findAllByMeeting(Meetings meeting);

    void deleteById(MeetingsEmployeeId id);
    // Add specific repository methods here, if needed
}

