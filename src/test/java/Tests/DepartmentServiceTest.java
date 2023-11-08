package Tests;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.service.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentService departmentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        departmentService = new DepartmentService(departmentRepository);
    }

    @Test
    public void testFindDepartmentById() {
        int departmentId = 1;
        Department department = new Department(departmentId, 10, "IT");
        Mockito.when(departmentRepository.findById(departmentId)).thenReturn(department);

        Department result = departmentService.findDepartmentById(departmentId);

        assertEquals(department, result);
    }

    @Test
    public void testFindNonExistentDepartmentById() {
        int departmentId = 2;
        Mockito.when(departmentRepository.findById(departmentId)).thenReturn(null);

        Department result = departmentService.findDepartmentById(departmentId);

        assertNull(result);
    }
}
