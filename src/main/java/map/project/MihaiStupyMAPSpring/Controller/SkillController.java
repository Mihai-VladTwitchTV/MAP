package map.project.MihaiStupyMAPSpring.Controller;

import java.util.List;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import map.project.MihaiStupyMAPSpring.data.dto.SkillDTO;
import map.project.MihaiStupyMAPSpring.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    // Get all skills
    @GetMapping()
    public List<Skill> listSkills() {
        return skillService.findAll();
    }

    // Add a new skill
    @PostMapping()
    public Skill addSkill(@RequestBody SkillDTO request) {
        Skill skill = new Skill();
        skill.setSkillName(request.getSkillName());
        skill.setDescription(request.getDescription());
        return skillService.save(skill);
    }

    // Find skill by ID
    @GetMapping("/{id}")
    public Skill findSkill(@PathVariable int id) {
        return skillService.findById(id);
    }

    // Delete skill by ID
    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable int id) {
        skillService.deleteById(id);
    }

    // Other CRUD operations can be added as necessary
}
