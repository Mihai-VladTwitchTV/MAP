package map.project.MihaiStupyMAPSpring.CLI;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class ComputerDirectoryCLI {

    @Autowired
    private DecoratorRepository decoratorRepository;

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private ComputerMonitorRepository computerMonitorRepository;

    @Autowired
    private MonitorAdapterRepository monitorAdapterRepository;

    @Autowired
    private TVRepository tvRepository;


    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @Override
    public String toString() {
        return "Computer CLI";
    }

    @ShellMethod(key = "list-computers", value = "List all computers")
    public String listAllComputers() {
        StringBuilder result = new StringBuilder("List of Computers:\n");
        Iterable<Computer> computers = computerRepository.findAll();
        eventPublisher.publishRepositoryMethodEvent(this);
        computers.forEach(computer -> result.append(computer.getId()).append(": ").append(computer.getBrandName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-computer", value = "Add a new computer")
    public String addComputer(
            @ShellOption({"-id", "--computerId"}) int computerId,
            @ShellOption({"-name", "--brandName"}) String brandName
    ) {
        Computer computer = new Computer(computerId, brandName);

        eventPublisher.publishRepositoryMethodEvent(this);
        computerRepository.save(computer);

        return "Computer added successfully.";
    }

    @ShellMethod(key = "update-computer", value = "Update a computer")
    public String updateComputer(
            @ShellOption({"-id", "--computerId"}) int computerId,
            @ShellOption({"-name", "--brandName"}) String brandName
    ) {
        eventPublisher.publishRepositoryMethodEvent(this);
        Computer computer = computerRepository.findById(computerId).orElse(null);
        if (computer != null) {
            computer.setBrandName(brandName);
            eventPublisher.publishRepositoryMethodEvent(this);
            computerRepository.save(computer);
            return "Computer updated successfully.";
        } else {
            return "Computer not found.";
        }
    }

    @ShellMethod(key = "delete-computer", value = "Delete a computer")
    public String deleteComputer(@ShellOption({"-id", "--computerId"}) int computerId) {
        Computer computer = computerRepository.findById(computerId).orElse(null);
        if (computer != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            computerRepository.delete(computer);
            return "Computer deleted successfully.";
        } else {
            return "Computer not found.";
        }
    }



    @ShellMethod(key = "connect-monitor",value = "Connect to a monitor or adapter")
    public String connectMonitor(@ShellOption({"--computerId"}) int computerID,@ShellOption({"--type"}) String type,@ShellOption({"--connectedId"}) String connectedID){
        Optional<Computer> computer = computerRepository.findById(computerID);
        if(computer.isEmpty()){
            return "No computer with inputted ID";
        }
        ExtendedComputerDecorator decor = null;
        List<ExtendedComputerDecorator> decs = decoratorRepository.findAll();
        for(ExtendedComputerDecorator dec : decs){
            if(dec.getComputer().getId() == computerID)
                decor = dec;
        }
        String extraInfo = "";
        if(decor!=null){
            extraInfo = extraInfo+" with connector of ID "+decor.getId()+", type "+decor.getConnectorType()+" and latency "+decor.getLatency();
        }
        else
            extraInfo = " with unknown connector ";


        switch (type) {
            case "monitor":
                Optional<ComputerMonitor> monitor = computerMonitorRepository.findById(Integer.valueOf(connectedID));
                if (monitor.isEmpty()) {
                    return "No monitor with inputted ID";
                }
                return computer.get().connect(monitor.get())+extraInfo;

            case "tv":
                Optional<TV> tv = tvRepository.findById(Integer.valueOf(connectedID));
                if (tv.isEmpty()) {
                    return "No TV with inputted ID";
                }
                List<TVToMonitorAdapter> adapters = monitorAdapterRepository.findAll();
                if(adapters.isEmpty()){
                    return "No Adapter for TV with inputted ID";

                }

                for (TVToMonitorAdapter adp : adapters) {
                    if (adp.getTv().getId() == Integer.parseInt(connectedID)) {
                        return(computer.get().connect(adp)+extraInfo);

                    }
                    return "No Adapter for TV with inputted ID";
                }
                break;
            default:
                return "Invalid option, please use 'monitor' or 'tv' ";
        }
        return null;

    }





}
