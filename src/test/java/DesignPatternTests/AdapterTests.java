package DesignPatternTests;

import map.project.MihaiStupyMAPSpring.CLI.AdapterDirectoryCLI;
import map.project.MihaiStupyMAPSpring.CLI.ComputerDirectoryCLI;
import map.project.MihaiStupyMAPSpring.data.adapterLogic.TVToMonitorAdapter;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Computer;
import map.project.MihaiStupyMAPSpring.data.baseClasses.ComputerMonitor;
import map.project.MihaiStupyMAPSpring.data.baseClasses.TV;
import map.project.MihaiStupyMAPSpring.data.decoratorLogic.DecoratorRepository;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.ComputerMonitorRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ComputerRepository;
import map.project.MihaiStupyMAPSpring.data.repository.MonitorAdapterRepository;
import map.project.MihaiStupyMAPSpring.data.repository.TVRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AdapterDirectoryCLI.class)
public class AdapterTests {

    @MockBean
    private DecoratorRepository decoratorRepository;

    @Autowired
    private AdapterDirectoryCLI adapterDirectoryCLI;

    @MockBean
    private ComputerDirectoryCLI computerCLI;

    @MockBean
    private ComputerRepository computerRepository;

    @MockBean
    private ComputerMonitorRepository computerMonitorRepository;

    @MockBean
    private MonitorAdapterRepository monitorAdapterRepository;

    @MockBean
    private RepositoryMethodEventPublisher repositoryMethodEventPublisher;

    @MockBean
    private TVRepository tvRepository;

//    @MockBean
//    private RepositoryMethodEventPublisher repositoryMethodEventPublisher;
//    @MockBean
//    private RepositoryMethodEventPublisher repositoryMethodEventPublisher;

    @Test
    public void testAddAdapter() {
        // Mocking the behavior of TVRepository
        when(tvRepository.findById(1)).thenReturn(Optional.of(new TV(1, "Samsung")));

        String result = adapterDirectoryCLI.addAdapter(1, 1);

        assertEquals("TV to Monitor Adapter added successfully.", result);
    }

    @Test
    public void testListAllAdapters() {
        // Mocking data in the repository
        when(monitorAdapterRepository.findAll()).thenReturn(
                List.of(
                        new TVToMonitorAdapter(1, new TV(1, "Samsung")),
                        new TVToMonitorAdapter(2, new TV(2, "LG"))
                )
        );

        String result = adapterDirectoryCLI.listAllAdapters();

        assertEquals("List of TV to Monitor Adapters:\n1: TV ID: 1, TV Brand: Samsung\n2: TV ID: 2, TV Brand: LG\n", result);
    }

    @Test
    public void testUpdateAdapter() {
        // Mocking the behavior of MonitorAdapterRepository
        when(monitorAdapterRepository.findById(1)).thenReturn(Optional.of(new TVToMonitorAdapter(1, new TV(1, "Samsung"))));

        String result = adapterDirectoryCLI.updateAdapter(1, 2, "LG");

        assertEquals("TV to Monitor Adapter updated successfully.", result);
    }

    @Test
    public void testDeleteAdapter() {
        // Mocking the behavior of MonitorAdapterRepository
        when(monitorAdapterRepository.findById(1)).thenReturn(Optional.of(new TVToMonitorAdapter(1, new TV(1, "Samsung"))));

        String result = adapterDirectoryCLI.deleteAdapter(1);

        assertEquals("TV to Monitor Adapter deleted successfully.", result);
    }

    @Test
    public void testConnectMonitor() {
        // Mocking data for the test
        when(computerRepository.findById(1)).thenReturn(Optional.of(new Computer(1, "Dell")));

        when(computerMonitorRepository.findById(2)).thenReturn(Optional.of(new ComputerMonitor(2, "Samsung")));

        when(tvRepository.findById(3)).thenReturn(Optional.of(new TV(3, "LG")));

        when(monitorAdapterRepository.findAll()).thenReturn(
                List.of(
                        new TVToMonitorAdapter(4, new TV(3, "LG"))
                )
        );

        when(computerCLI.connectMonitor(eq(1), eq("monitor"), eq("2")))
                .thenReturn("Computer with ID 1 and brand Dell connected to Monitor with ID 2 and brand Samsung with unknown connector ");

        when(computerCLI.connectMonitor(eq(1), eq("tv"), eq("3")))
                .thenReturn("Computer with ID 1 and brand Dell connected to TV with ID 3 and brand LG through adapter with id 4 with unknown connector ");

        String result = computerCLI.connectMonitor(1, "monitor", "2");

        System.out.println("Result: " + result);
        System.out.println("Decor: " + decoratorRepository.findAll());

        assertEquals("Computer with ID 1 and brand Dell connected to Monitor with ID 2 and brand Samsung with unknown connector ", result);

        result = computerCLI.connectMonitor(1, "tv", "3");

        assertEquals("Computer with ID 1 and brand Dell connected to TV with ID 3 and brand LG through adapter with id 4 with unknown connector ", result);
    }
}

