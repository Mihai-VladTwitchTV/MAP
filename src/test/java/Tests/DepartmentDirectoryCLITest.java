package Tests;

import map.project.MihaiStupyMAPSpring.CLI.DepartmentDirectoryCLI;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.shell.Shell;

import javax.print.DocFlavor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentDirectoryCLITest {

    @InjectMocks
    private DepartmentDirectoryCLI departmentDirectoryCLI;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private Shell shell;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDepartment() {
        Department department = new Department();
        department.setDepartmentID(1);
        department.setMaxEmployees(100);
        department.setSpecialization("Engineering");

        Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);

        String result = departmentDirectoryCLI.addDepartment(1, 100, "Engineering");
        assertEquals("Department added successfully.", result);
    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        department.setDepartmentID(1);
        department.setMaxEmployees(100);
        department.setSpecialization("Engineering");

        Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);

        String result = departmentDirectoryCLI.addDepartment(1, 100, "Engineering");
        assertEquals("Department added successfully.", result);


        department.setSpecialization("IT");
        department.setMaxEmployees(20);
        int maxEmployees = department.getMaxEmployees();
        String specialization = department.getSpecialization();
        assertEquals(maxEmployees,20);
        assertEquals(specialization,"IT");


    }
}
