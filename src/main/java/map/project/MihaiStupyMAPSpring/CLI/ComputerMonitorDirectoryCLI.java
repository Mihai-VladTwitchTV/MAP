package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.ComputerMonitor;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.ComputerMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ComputerMonitorDirectoryCLI {

    @Autowired
    private ComputerMonitorRepository computerMonitorRepository;


    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @Override
    public String toString() {
        return "ComputerMonitor CLI";
    }

    @ShellMethod(key = "list-computer-monitors", value = "List all computer monitors")
    public String listAllComputerMonitors() {
        StringBuilder result = new StringBuilder("List of Computer Monitors:\n");
        Iterable<ComputerMonitor> computerMonitors = computerMonitorRepository.findAll();
        eventPublisher.publishRepositoryMethodEvent(this);
        computerMonitors.forEach(computerMonitor -> result.append(computerMonitor.getId()).append(": ").append(computerMonitor.getBrandName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-computer-monitor", value = "Add a new computer monitor")
    public String addComputerMonitor(
            @ShellOption({"-id", "--monitorId"}) int monitorId,
            @ShellOption({"-name", "--brandName"}) String brandName
    ) {
        ComputerMonitor computerMonitor = new ComputerMonitor(monitorId, brandName);

        eventPublisher.publishRepositoryMethodEvent(this);
        computerMonitorRepository.save(computerMonitor);

        return "Computer Monitor added successfully.";
    }

    @ShellMethod(key = "update-computer-monitor", value = "Update a computer monitor")
    public String updateComputerMonitor(
            @ShellOption({"-id", "--monitorId"}) int monitorId,
            @ShellOption({"-name", "--brandName"}) String brandName
    ) {
        eventPublisher.publishRepositoryMethodEvent(this);
        ComputerMonitor computerMonitor = computerMonitorRepository.findById(monitorId).orElse(null);
        if (computerMonitor != null) {
            computerMonitor.setBrandName(brandName);
            eventPublisher.publishRepositoryMethodEvent(this);
            computerMonitorRepository.save(computerMonitor);
            return "Computer Monitor updated successfully.";
        } else {
            return "Computer Monitor not found.";
        }
    }

    @ShellMethod(key = "delete-computer-monitor", value = "Delete a computer monitor")
    public String deleteComputerMonitor(@ShellOption({"-id", "--monitorId"}) int monitorId) {
        ComputerMonitor computerMonitor = computerMonitorRepository.findById(monitorId).orElse(null);
        if (computerMonitor != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            computerMonitorRepository.delete(computerMonitor);
            return "Computer Monitor deleted successfully.";
        } else {
            return "Computer Monitor not found.";
        }
    }
}
