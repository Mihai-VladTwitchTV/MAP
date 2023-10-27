package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.ProjectCosts;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectCostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectCostsService extends BaseService<ProjectCosts, Integer> {
    @Autowired
    public ProjectCostsService(ProjectCostsRepository repository) {
        super(repository);
    }

    // Add specific service methods for ProjectCosts, if needed
}

