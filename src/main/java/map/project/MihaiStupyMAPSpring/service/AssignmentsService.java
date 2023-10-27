package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentsService extends BaseService<Assignments, Integer> {
    @Autowired
    public AssignmentsService(AssignmentsRepository repository) {
        super(repository);
    }

    // Add specific service methods for Assignments, if needed
}