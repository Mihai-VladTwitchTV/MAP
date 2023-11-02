package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.DepartmentLeader;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentLeaderService extends BaseService<DepartmentLeader, Integer> {
    @Autowired
    public DepartmentLeaderService(EmployeeRepository repository) {
        super(repository);
    }

    // Add specific service methods for DepartmentLeader, if needed
}
