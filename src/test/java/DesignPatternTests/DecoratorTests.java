package DesignPatternTests;

import map.project.MihaiStupyMAPSpring.CLI.AdapterDirectoryCLI;
import map.project.MihaiStupyMAPSpring.CLI.ComputerDirectoryCLI;
import map.project.MihaiStupyMAPSpring.data.adapterLogic.TVToMonitorAdapter;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Computer;
import map.project.MihaiStupyMAPSpring.data.baseClasses.ComputerMonitor;
import map.project.MihaiStupyMAPSpring.data.baseClasses.TV;
import map.project.MihaiStupyMAPSpring.data.decoratorLogic.DecoratorRepository;
import map.project.MihaiStupyMAPSpring.data.decoratorLogic.ExtendedComputerDecorator;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.ComputerMonitorRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ComputerRepository;
import map.project.MihaiStupyMAPSpring.data.repository.MonitorAdapterRepository;
import map.project.MihaiStupyMAPSpring.data.repository.TVRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AdapterDirectoryCLI.class)
public class DecoratorTests {

    @MockBean
    private MonitorAdapterRepository monitorAdapterRepository;

    @MockBean
    private RepositoryMethodEventPublisher repositoryMethodEventPublisher;

    @Mock
    private ComputerRepository computerRepository;

    @Mock
    private ComputerMonitorRepository computerMonitorRepository;


    @MockBean
    private TVRepository tvRepository;


    @Mock
    private DecoratorRepository decoratorRepository;

    //@InjectMocks
    @Mock
    private ComputerDirectoryCLI computerCLI;

    @Test
    public void testConnectMonitorWithDecoratorInfo() {
        // Mocking data for the test
        doReturn(Optional.of(new Computer(1, "Dell"))).when(computerRepository).findById(1);
        when(tvRepository.findById(3)).thenReturn(Optional.of(new TV(3, "LG")));

        when(monitorAdapterRepository.findAll()).thenReturn(
                List.of(
                        new TVToMonitorAdapter(4, new TV(3, "LG"))
                )
        );

        // Mock decorator data
        when(decoratorRepository.findAll()).thenReturn(
                List.of(
                        new ExtendedComputerDecorator(5, new Computer(1, "Dell"), "HDMI", 10)
                )
        );

        // Mock the connectMonitor method
        doReturn("Computer with ID 1 and brand Dell connected to Monitor with ID 2 and brand Samsung with unknown connector with connector of ID 5, type HDMI and latency 10")
                .when(computerCLI)
                .connectMonitor(1, "monitor", "2");

        String result = computerCLI.connectMonitor(1, "monitor", "2");

        System.out.println("Result: " + result);

        assertEquals("Computer with ID 1 and brand Dell connected to Monitor with ID 2 and brand Samsung with unknown connector with connector of ID 5, type HDMI and latency 10", result);
    }
}
