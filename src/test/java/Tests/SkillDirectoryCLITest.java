package Tests;

import map.project.MihaiStupyMAPSpring.CLI.SkillDirectoryCLI;
import map.project.MihaiStupyMAPSpring.CLI.DepartmentDirectoryCLI;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Skill;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.SkillRepository;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.shell.Input;
import org.springframework.shell.Shell;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.commands.Help;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SkillDirectoryCLITest {

    @InjectMocks
    private SkillDirectoryCLI skillDirectoryCLI;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private Shell shell;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSkill() {
        Skill skill = new Skill();
        skill.setSkillID(1);
        skill.setSkillName("Java");
        skill.setDescription("Programming Language");

        Mockito.when(skillRepository.save(Mockito.any(Skill.class))).thenReturn(skill);

        String result = skillDirectoryCLI.addSkill(1, "Java", "Programming Language");
        assertEquals("Skill added successfully.", result);
    }

    @Test
    public void testUpdateSkill() {
        Skill skill = new Skill();
        skill.setSkillID(1);
        skill.setSkillName("Java");
        skill.setDescription("Programming Language");

        Mockito.when(skillRepository.save(Mockito.any(Skill.class))).thenReturn(skill);

        String result = skillDirectoryCLI.addSkill(1, "Java", "Programming Language");
        assertEquals("Skill added successfully.", result);

        skill.setSkillName("IT");
        skill.setSkillID(20);
        int id = skill.getSkillID();
        String name= skill.getSkillName();
        assertEquals(id,20);
        assertEquals(name,"IT");
    }

}
