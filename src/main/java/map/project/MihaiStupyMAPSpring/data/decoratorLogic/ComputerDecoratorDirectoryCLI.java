package map.project.MihaiStupyMAPSpring.data.decoratorLogic;

import com.sun.jdi.connect.Connector;
import map.project.MihaiStupyMAPSpring.data.decoratorLogic.ExtendedComputerDecorator;
import map.project.MihaiStupyMAPSpring.data.decoratorLogic.DecoratorRepository;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Computer;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class ComputerDecoratorDirectoryCLI {

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private DecoratorRepository decoratorRepository;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @Override
    public String toString() {
        return "Computer Decorator CLI";
    }

    @ShellMethod(key = "list-connectors", value = "List all connectors")
    public String listAllConnectors() {
        eventPublisher.publishRepositoryMethodEvent(this);
        Iterable<ExtendedComputerDecorator> decoratedComputers = decoratorRepository.findAll();
        StringBuilder result = new StringBuilder("List of Connectors:\n");
        decoratedComputers.forEach(decoratedComputer ->
                result.append(decoratedComputer.getId()).append(": ")
                        .append("Computer ID: ").append(decoratedComputer.getComputer().getId()).append(", ")
                        .append("Connector Type: ").append(decoratedComputer.getConnectorType()).append(", ")
                        .append("Latency: ").append(decoratedComputer.getLatency()).append("\n")
        );
        return result.toString();
    }

    @ShellMethod(key = "add-connector", value = "Add or update connector to a computer")
    public String decorateComputer(
            @ShellOption(value = "connectorId", help = "Connector Id") int connectorID,
            @ShellOption(value = "computerId", help = "Computer Id") int computerID,
            @ShellOption(value = "connectorType", help = "Connector Type") String connectorType,
            @ShellOption(value = "latency", help = "Latency") int latency) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Optional<Computer> comp = computerRepository.findById(computerID);
        if(comp.isEmpty())
            return "No Computer with said Id exists ";
        List<ExtendedComputerDecorator> decs = decoratorRepository.findAll();
        for(ExtendedComputerDecorator dec : decs){
            if(dec.getComputer().getId() == computerID)
                return "Computer already has a connector with ID "+dec.getId();
        }

        ExtendedComputerDecorator decoratedComputer = new ExtendedComputerDecorator();

        decoratedComputer.setId(connectorID);
        decoratedComputer.setComputer(comp.get());
        decoratedComputer.setConnectorType(connectorType);
        decoratedComputer.setLatency(latency);

        decoratorRepository.save(decoratedComputer);


        return "Computer connector added successfully.";


    }

    @ShellMethod(key = "update-connector", value = "Update functionality of a connector")
    public String updateDecoratedComputer(
            @ShellOption(value = "connectorId", help = "Connector ID") int decoratedComputerID,
            @ShellOption(value = "connectorType", help = "Connector Type") String connectorType,
            @ShellOption(value = "latency", help = "Latency") int latency) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Optional<ExtendedComputerDecorator> decoratedComputerOptional = decoratorRepository.findById(decoratedComputerID);

        if (decoratedComputerOptional.isPresent()) {
            ExtendedComputerDecorator decoratedComputer = decoratedComputerOptional.get();
            decoratedComputer.setConnectorType(connectorType);
            decoratedComputer.setLatency(latency);
            decoratorRepository.save(decoratedComputer);
            return "Decorated Computer updated successfully.";
        } else {
            return "Decorated Computer not found.";
        }
    }

    @ShellMethod(key = "delete-connector", value = "Delete connector functionality of a computer")
    public String deleteDecoratedComputer(@ShellOption(value = "connectorComputerId", help = "Decorated Computer ID") int decoratedComputerID) {
        eventPublisher.publishRepositoryMethodEvent(this);
        Optional<ExtendedComputerDecorator> decoratedComputerOptional = decoratorRepository.findById(decoratedComputerID);

        if (decoratedComputerOptional.isPresent()) {
            eventPublisher.publishRepositoryMethodEvent(this);
            decoratorRepository.delete(decoratedComputerOptional.get());
            return "Connector-Computer functionality deleted successfully.";
        } else {
            return "Connector-Computer functionality not found.";
        }
    }

//    public ExtendedComputerDecorator getDecoratorForComputer(int computerId) {
//        List<ExtendedComputerDecorator> decs = decoratorRepository.findAll();
//        if (decs.isEmpty())
//            return null;
//        for (ExtendedComputerDecorator dec : decs) {
//            if (dec.getComputer().getId() == computerId)
//                return new ExtendedComputerDecorator(dec.getId(), dec.getComputer(),dec.getConnectorType(), dec.getLatency());
//        }
//        return null;
//
//    }
}
