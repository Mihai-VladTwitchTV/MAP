package PermanentStorageTests;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.service.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Spy
    private DepartmentRepository departmentRepository;

    @InjectMocks
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

        when(departmentRepository.findById(departmentId)).thenReturn((department));

        Department result = departmentService.findDepartmentById(departmentId);

        assertEquals(department, result);
    }


    @Test
    public void testFindNonExistentDepartmentById() {
        int departmentId = 2;
//        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        Department result = departmentService.findDepartmentById(departmentId);

        assertNull(result);
    }

    @Test
    public void testUpdateDepartment() {
        Department existingDepartment = new Department(1, 10, "IT");
        when(departmentRepository.findById(anyInt())).thenReturn((existingDepartment));
        when(departmentRepository.save(any())).thenReturn(existingDepartment);

        Department updatedDepartment = departmentService.updateDepartment(1, new Department(1, 20, "New IT"));

        assertEquals(existingDepartment, updatedDepartment);
        verify(departmentRepository, times(1)).findById(anyInt());
        verify(departmentRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateNonExistentDepartment() {
        Department nonExistentDepartment = new Department(2, 20, "HR");
//        when(departmentRepository.findById(anyInt())).thenReturn(empty());

        Department updatedDepartment = departmentService.updateDepartment(2, nonExistentDepartment);

        assertNull(updatedDepartment);
        verify(departmentRepository, times(1)).findById(anyInt());
        verify(departmentRepository, never()).save(any());
    }

    @Test
    public void testDeleteDepartment() {
        int departmentId = 1;
        Department existingDepartment = new Department(departmentId, 10, "IT");
//        when(departmentRepository.findById(anyInt())).thenReturn((existingDepartment));

        departmentService.deleteDepartmentById(departmentId);

        verify(departmentRepository, times(1)).deleteById(anyInt());
    }


    @Test
    public void testDeleteNonExistentDepartment() {
        int nonExistentDepartmentId = 2;
//        when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());

        departmentService.deleteDepartmentById(nonExistentDepartmentId);


        verify(departmentRepository).deleteById(anyInt());
    }

    @Test
    public void testFindAllDepartments() {
        List<Department> departmentList = Collections.singletonList(new Department(1, 10, "IT"));
        when(departmentRepository.findAll()).thenReturn(departmentList);

        List<Department> result = departmentService.findAllDepartments();

        assertEquals(departmentList, result);
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllDepartmentsEmptyList() {
        when(departmentRepository.findAll()).thenReturn(Collections.emptyList());

        List<Department> result = departmentService.findAllDepartments();

        assertTrue(result.isEmpty());
        verify(departmentRepository, times(1)).findAll();
    }
}
