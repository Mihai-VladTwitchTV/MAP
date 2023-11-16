package Tests;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import map.project.MihaiStupyMAPSpring.data.repository.SkillRepository;
import map.project.MihaiStupyMAPSpring.service.SkillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = SkillService.class)
class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    private SkillService skillService;

    @Test
    void testAddSkill() {
        Skill skill = new Skill();
        skill.setSkillID(1);
        skill.setSkillName("Java");
        skill.setDescription("Programming Language");

        when(skillRepository.save(skill)).thenReturn(skill);

        skillService.save(skill);

        verify(skillRepository, times(1)).save(skill);

        Skill savedSkill = skillRepository.findById(1).orElse(null);
        assertNotNull(savedSkill);
        assertEquals("Java", savedSkill.getSkillName());
    }

    @Test
    void testListSkills() {
        Skill skill1 = new Skill();
        skill1.setSkillID(1);
        skill1.setSkillName("Java");
        skill1.setDescription("Programming Language");

        Skill skill2 = new Skill();
        skill2.setSkillID(2);
        skill2.setSkillName("Java");
        skill2.setDescription("Programming Language");

        Skill skill3 = new Skill();
        skill3.setSkillID(111);
        skill3.setSkillName("JMd");
        skill3.setDescription("Programming Language");

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);

        when(skillRepository.findAll()).thenReturn(skills);

        List<Skill> skillList = skillService.findAll();
        assertEquals(3, skillList.size());
    }

    // Additional tests for other functionalities can be added based on your requirements.
}
