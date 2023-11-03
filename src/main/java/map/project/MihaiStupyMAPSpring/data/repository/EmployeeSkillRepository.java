package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeSkill;
import map.project.MihaiStupyMAPSpring.data.baseClasses.EmployeeSkillId;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSkillRepository extends BaseRepository<EmployeeSkill, Integer> {
    EmployeeSkill findById(EmployeeSkillId employeeSkillId);
    // Add specific repository methods here, if needed
}
