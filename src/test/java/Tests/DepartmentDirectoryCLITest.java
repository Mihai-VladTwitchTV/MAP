package Tests;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.CLI.DepartmentDirectoryCLI;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartmentDirectoryCLITest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentDirectoryCLI departmentDirectoryCLI;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        departmentDirectoryCLI = new DepartmentDirectoryCLI(departmentRepository); // Inject the mock repository
    }

    @Test
    public void testListAllDepartments() {
        Department department1 = new Department(1, 10, "IT");
        Department department2 = new Department(2, 15, "HR");

        Mockito.when(departmentRepository.findAll()).thenReturn(List.of(department1, department2));

        String result = departmentDirectoryCLI.listAllDepartments();

        String expected = "List of Departments:\n1: IT\n2: HR\n";
        assertEquals(expected, result);
    }

    @Test
    public void testAddDepartment() {
        int departmentId = 1;
        int maxEmployees = 10;
        String specialization = "IT";

        String result = departmentDirectoryCLI.addDepartment(departmentId, maxEmployees, specialization);

        assertEquals("Department added successfully.", result);
        Mockito.verify(departmentRepository).save(Mockito.any());
    }

    // Add similar tests for updateDepartment and deleteDepartment methods.
}
