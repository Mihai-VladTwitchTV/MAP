package DesignPatternTests;

import map.project.MihaiStupyMAPSpring.CLI.DepartmentDirectoryCLI;
import map.project.MihaiStupyMAPSpring.CLI.EmployeeDirectoryCLI;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryObserver;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import map.project.MihaiStupyMAPSpring.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {


    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentDirectoryCLI departmentDirectoryCLI;
    @InjectMocks
    private EmployeeDirectoryCLI employeeDirectoryCLI;
    @InjectMocks
    private RepositoryObserver repositoryObserver;

    @Mock
    private RepositoryMethodEventPublisher repositoryMethodEventPublisher;

    @BeforeEach
    public void init(){
        lenient().doNothing().when(repositoryMethodEventPublisher).publishRepositoryMethodEvent(any());
    }

    @Test
    public void testGetAllEmployees() {
        // Mocking the behavior of the repository
        Department department1 = new Department(1, 15, "HR");
        when(departmentRepository.save(department1)).thenReturn(department1);

        Employee employee1 = new Employee.EmployeeBuilder(1, "Stupariu", "Bogdan", 775355032, "stup@", department1)
                .type("lead")
                .build();
        Employee employee2 = new Employee.EmployeeBuilder(2, "Fain", "CIENVA", 765355032, "stup@", department1)
                .type("part-time")
                .build();

        when(employeeDirectoryCLI.listAllEmployees()).thenReturn("List of Employees:\n" +
                "1: Stupariu Bogdan (Role: Lead, Department: HR)\n" +
                "2: Fain CIENVA (Role: Part-time, Department: HR)\n");

        String allEmployees = employeeDirectoryCLI.listAllEmployees();

        String expectedOutput = "List of Employees:\n" +
                "1: Stupariu Bogdan (Role: Lead, Department: HR)\n" +
                "2: Fain CIENVA (Role: Part-time, Department: HR)\n";
        assertEquals(expectedOutput, allEmployees);

        verify(departmentRepository, times(1)).save(department1);
        verify(employeeDirectoryCLI, times(1)).listAllEmployees();
    }

    @Test
    public void testGetEmployeeById() {
        // Mocking the behavior of the repository
        int employeeId = 1;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(new Employee()));

        // Performing the service method
        Optional<Employee> employee = employeeService.getById(employeeId);

        // Verifying the results
        assertTrue(employee.isPresent());
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    public void testSaveEmployee() {
        // Mocking the behavior of the repository
        Employee employeeToSave = new Employee();
        when(employeeRepository.save(employeeToSave)).thenReturn(employeeToSave);

        // Performing the service method
        Employee savedEmployee = employeeService.save(employeeToSave);

        // Verifying the results
        assertNotNull(savedEmployee);
        verify(employeeRepository, times(1)).save(employeeToSave);
    }

    @Test
    public void testDeleteEmployee() {
        // Mocking the behavior of the repository
        int employeeIdToDelete = 1;

        // Performing the service method
        employeeService.delete(employeeIdToDelete);

        // Verifying the results
        verify(employeeRepository, times(1)).deleteById(employeeIdToDelete);
    }
}
