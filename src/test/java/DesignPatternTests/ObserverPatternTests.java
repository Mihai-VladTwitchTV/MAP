package DesignPatternTests;

import map.project.MihaiStupyMAPSpring.CLI.DepartmentDirectoryCLI;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryObserver;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.sorterLogic.DepartmentSorter;
import map.project.MihaiStupyMAPSpring.data.sorterLogic.EmployeeSorter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ObserverPatternTests {
    @Mock
    private DepartmentDirectoryCLI departmentDirectoryCLI;

    @InjectMocks
    private RepositoryObserver observer;


    @Test
    public void AddDepartmentObserve() {
        // Clear previous observed events
        observer.clearEvents();

        // Create and save departments
        departmentDirectoryCLI.addDepartment(12, 15, "HR");
        departmentDirectoryCLI.addDepartment(16, 15, "IT");

        // Verify that two events were observed
        assertEquals(2, observer.getObservedEvents().size());

        // Optionally, you can print the observed events for debugging
        for (String event : observer.getObservedEvents()) {
            System.out.println(event);
        }
    }
}
