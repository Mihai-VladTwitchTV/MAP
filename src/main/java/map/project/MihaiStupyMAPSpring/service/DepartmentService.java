package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseService<Department, Integer> {
    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        super(repository);
    }

    // Add specific service methods for Department, if needed
}

