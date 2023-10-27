package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Meetings;
import map.project.MihaiStupyMAPSpring.data.repository.MeetingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingsService extends BaseService<Meetings, Integer> {
    @Autowired
    public MeetingsService(MeetingsRepository repository) {
        super(repository);
    }

    // Add specific service methods for Meetings, if needed
}
