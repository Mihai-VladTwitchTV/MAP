package map.project.MihaiStupyMAPSpring.CLI;

import com.sun.jdi.connect.Connector;
import map.project.MihaiStupyMAPSpring.data.decoratorLogic.ExtendedComputerDecorator;
import map.project.MihaiStupyMAPSpring.data.decoratorLogic.DecoratorRepository;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Computer;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class ComputerDecoratorDirectoryCLI {

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
        StringBuilder result = new StringBuilder("List of Decorated Computers:\n");
        decoratedComputers.forEach(decoratedComputer ->
                result.append(decoratedComputer.getId()).append(": ")
                        .append("Connector ID: ").append(decoratedComputer.getConnectorId()).append(", ")
                        .append("Computer ID: ").append(decoratedComputer.getComputer().getId()).append(", ")
                        .append("Connector Type: ").append(decoratedComputer.getConnectorType()).append(", ")
                        .append("Latency: ").append(decoratedComputer.getLatency()).append("\n")
        );
        return result.toString();
    }

    @ShellMethod(key = "add-computer-connector", value = "Add or update connector to a computer")
    public String decorateComputer(
            @ShellOption(value = "computerID", help = "Computer ID") int computerID,
            @ShellOption(value = "connectorType", help = "Connector Type") String connectorType,
            @ShellOption(value = "latency", help = "Latency") int latency) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Optional<Computer> computerOptional = decoratorRepository.findById(computerID).map(ExtendedComputerDecorator::getComputer);

        if (computerOptional.isPresent()) {
            Computer computer = computerOptional.get();

            Optional<ExtendedComputerDecorator> existingDecorator = decoratorRepository.findById(computerID);

            ExtendedComputerDecorator decoratedComputer = existingDecorator.orElseGet(() -> new ExtendedComputerDecorator());

            decoratedComputer.setComputer(computer);
            decoratedComputer.setConnector(connectorType, latency);

            decoratorRepository.save(decoratedComputer);
            eventPublisher.publishRepositoryMethodEvent(this);

            return "Computer decorated successfully.";
        } else {
            return "Computer not found.";
        }
    }

    @ShellMethod(key = "update-decorated-computer", value = "Update functionality of a decorated computer")
    public String updateDecoratedComputer(
            @ShellOption(value = "decoratedComputerID", help = "Decorated Computer ID") int decoratedComputerID,
            @ShellOption(value = "connectorType", help = "Connector Type") String connectorType,
            @ShellOption(value = "latency", help = "Latency") int latency) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Optional<ExtendedComputerDecorator> decoratedComputerOptional = decoratorRepository.findById(decoratedComputerID);

        if (decoratedComputerOptional.isPresent()) {
            ExtendedComputerDecorator decoratedComputer = decoratedComputerOptional.get();
            decoratedComputer.setConnector(connectorType, latency);

            eventPublisher.publishRepositoryMethodEvent(this);
            decoratorRepository.save(decoratedComputer);
            return "Decorated Computer updated successfully.";
        } else {
            return "Decorated Computer not found.";
        }
    }

    @ShellMethod(key = "delete-decorated-computer", value = "Delete decorated functionality of a computer")
    public String deleteDecoratedComputer(@ShellOption(value = "decoratedComputerID", help = "Decorated Computer ID") int decoratedComputerID) {
        eventPublisher.publishRepositoryMethodEvent(this);
        Optional<ExtendedComputerDecorator> decoratedComputerOptional = decoratorRepository.findById(decoratedComputerID);

        if (decoratedComputerOptional.isPresent()) {
            eventPublisher.publishRepositoryMethodEvent(this);
            decoratorRepository.delete(decoratedComputerOptional.get());
            return "Decorated Computer functionality deleted successfully.";
        } else {
            return "Decorated Computer functionality not found.";
        }
    }

    public ExtendedComputerDecorator getDecoratorForComputer(int computerId){
        List<ExtendedComputerDecorator> decs = decoratorRepository.findAll();
        if(decs.isEmpty())
            return null;
        for(ExtendedComputerDecorator dec : decs){
            if(dec.getComputer().getId() == computerId){
                Computer comp = dec.getComputer();
                Connector conn = (Connector)dec.getConnector();
                return new ExtendedComputerDecorator(dec.getId(),);

            }
        }
    }
}
