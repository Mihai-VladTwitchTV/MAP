package PermanentStorageTests;

import map.project.MihaiStupyMAPSpring.CLI.AssignmentsDirectoryCLI;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes= Assignments.class)
@ActiveProfiles("test")
@ComponentScan(basePackages = "map.project.MihaiStupyMAPSpring")
public class AssignmentsIntegrationTest {

    @Autowired
    private AssignmentsDirectoryCLI assignmentsDirectoryCLI;

    @Test
    public void countAllAssignments_IntegrationTest() {
        int count = assignmentsDirectoryCLI.countAllAssignments();

        assertEquals( 2, count);
    }
}
