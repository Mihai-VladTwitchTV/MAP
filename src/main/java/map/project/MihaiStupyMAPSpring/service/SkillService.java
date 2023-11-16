package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import map.project.MihaiStupyMAPSpring.data.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService extends BaseService<Skill, Integer> {

    @Autowired
    public SkillService(SkillRepository repository) {
        super(repository);
    }

    // Add specific service methods for Skill, if needed

    public void deleteById(int skillId) {
        Skill skill = findById(skillId);

        if (skill != null) {
            getRepository().delete(skill);
        } else {
            throw new IllegalArgumentException("Skill with ID " + skillId + " not found");
        }
    }
}
