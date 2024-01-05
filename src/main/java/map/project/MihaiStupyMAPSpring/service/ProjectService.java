package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService extends BaseService<Project, Integer> {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository repository) {
        super(repository);
        this.projectRepository = repository;
    }
    public void delete(int id) {
        projectRepository.deleteById(id);
    }

    public Optional<Project> findById(int id) {
        return projectRepository.findById(id);
    }

    // Add specific service methods for Project, if needed
}
