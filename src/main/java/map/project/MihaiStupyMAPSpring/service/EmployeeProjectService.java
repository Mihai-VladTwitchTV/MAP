package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeProject;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProjectService extends BaseService<EmployeeProject, Integer> {
    @Autowired
    public EmployeeProjectService(EmployeeProjectRepository repository) {
        super(repository);
    }

    // Add specific service methods for EmployeeProject, if needed
}

