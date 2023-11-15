package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeSkill;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeSkillRepository;
import map.project.MihaiStupyMAPSpring.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSkillService extends BaseService<EmployeeSkill, Integer> {
    @Autowired
    public EmployeeSkillService(EmployeeSkillRepository repository) {
        super(repository);
    }

    // Add specific service methods for EmployeeSkill, if needed
}
