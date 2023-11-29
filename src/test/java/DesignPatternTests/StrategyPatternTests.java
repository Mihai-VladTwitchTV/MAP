package DesignPatternTests;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.sorterLogic.DepartmentSorter;
import map.project.MihaiStupyMAPSpring.data.sorterLogic.EmployeeSorter;
import map.project.MihaiStupyMAPSpring.data.sorterLogic.SorterCommands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StrategyPatternTests {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DepartmentSorter departmentSorter;

    @InjectMocks
    private EmployeeSorter employeeSorter;

    @InjectMocks
    private SorterCommands sorterCommands;

    @BeforeEach
    public void setUp() {
        sorterCommands = mock(SorterCommands.class);
    }
    @Test
    public void testDepartmentSorter() {
        // Mocking the behavior of departmentRepository
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(new Department(12, 15, "HR"));
        departmentList.add(new Department(1, 20, "IT"));

        when(departmentRepository.findAll()).thenReturn(departmentList);

        // Testing the departmentSorter
        List<Department> sortedDepartments = departmentSorter.getSortedRepo();

        // Verify that departmentRepository.findAll() is called
        verify(departmentRepository, times(1)).findAll();
        assertEquals(sortedDepartments.get(0).getDepartmentID(),1);
        assertEquals(sortedDepartments.get(1).getDepartmentID(),12);
    }

    @Test
    public void testEmployeeSorter() {
        // Mocking the behavior of employeeRepository
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee.EmployeeBuilder(10, "John", "Doe", 123456789, "john.doe@example.com", new Department())
                .type("full-time")
                .isFullTime(true)
                .build());
        employeeList.add(new Employee.EmployeeBuilder(8, "Jane", "Smith", 987654321, "jane.smith@example.com", new Department())
                .type("lead")
                .isLeader(true)
                .build());

        when(employeeRepository.findAll()).thenReturn(employeeList);

        // Testing the employeeSorter
        List<Employee> sortedEmployees = employeeSorter.getSortedRepo();

        // Verify that employeeRepository.findAll() is called
        verify(employeeRepository, times(1)).findAll();

        // Verify that the employees are sorted by DepartmentID
        assertEquals(sortedEmployees.get(0).getEmployeeID(), 8);
        assertEquals(sortedEmployees.get(1).getEmployeeID(), 10); // Assuming both employees are in the same department
    }

}
