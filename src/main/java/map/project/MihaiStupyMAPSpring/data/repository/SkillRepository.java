package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends BaseRepository<Skill, Integer> {
    // Add specific repository methods here, if needed
}

