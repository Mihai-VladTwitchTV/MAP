package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends BaseService<Project, Integer> {
    @Autowired
    public ProjectService(ProjectRepository repository) {
        super(repository);
    }

    // Add specific service methods for Project, if needed
}
