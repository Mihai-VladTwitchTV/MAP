package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.ProjectMilestones;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectMilestonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectMilestonesService extends BaseService<ProjectMilestones, Integer> {
    @Autowired
    public ProjectMilestonesService(ProjectMilestonesRepository repository) {
        super(repository);
    }

    // Add specific service methods for ProjectMilestones, if needed
}

