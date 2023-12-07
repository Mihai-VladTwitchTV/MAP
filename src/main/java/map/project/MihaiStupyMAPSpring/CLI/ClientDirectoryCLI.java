package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import map.project.MihaiStupyMAPSpring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@ShellComponent
public class ClientDirectoryCLI {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @ShellMethod(key = "list-clients", value = "List all clients")
    public String listAllClients() {
        StringBuilder result = new StringBuilder("List of Clients:\n");
        Iterable<Client> clients = clientRepository.findAll();
        eventPublisher.publishRepositoryMethodEvent(this);
        clients.forEach(client -> result.append(client.getClientID()).append(": ").append(client.getFirstName()).append(" ").append(client.getLastName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-client", value = "Add a new client")
    public String addClient(
            @ShellOption({"-id", "--clientID"}) int clientID,
            @ShellOption({"-first", "--firstName"}) String firstName,
            @ShellOption({"-last", "--lastName"}) String lastName,
            @ShellOption({"-email", "--emailAddress"}) String emailAddress,
            @ShellOption({"-phone", "--phoneNumber"}) int phoneNumber) {

        Client client = new Client(clientID, firstName, lastName, emailAddress, phoneNumber);

//        // Parse date strings into Date objects
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate = null;
//        Date endDate = null;
//        try {
//            startDate = dateFormat.parse(startDateStr);
//            endDate = dateFormat.parse(endDateStr);
//        } catch (ParseException e) {
//            // Handle the exception according to your needs
//            e.printStackTrace();
//        }

        eventPublisher.publishRepositoryMethodEvent(this);
        clientService.saveClient(client);
//        clientService.saveWithProject(
//                projectName, client, projectID, status, startDate, endDate, departmentID, meetingType);

        return "Client added successfully ";
    }




    @ShellMethod(key = "update-client", value = "Update a client")
    public String updateClient(@ShellOption({"-id", "--clientID"}) int clientID, @ShellOption({"-first", "--firstName"}) String firstName, @ShellOption({"-last", "--lastName"}) String lastName, @ShellOption({"-email", "--emailAddress"}) String emailAddress, @ShellOption({"-phone", "--phoneNumber"}) int phoneNumber) {
        eventPublisher.publishRepositoryMethodEvent(this);
        Client client = clientRepository.findById(clientID).orElse(null);
        if (client != null) {
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setEmailAddress(emailAddress);
            client.setPhoneNumber(phoneNumber);
            eventPublisher.publishRepositoryMethodEvent(this);
            clientRepository.save(client);
            return "Client updated successfully.";
        } else {
            return "Client not found.";
        }
    }

    @ShellMethod(key = "delete-client", value = "Delete a client")
    public String deleteClient(@ShellOption({"-id", "--clientID"}) int clientID) {
        Client client = clientRepository.findById(clientID).orElse(null);
        if (client != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            clientRepository.delete(client);
            return "Client deleted successfully.";
        } else {
            return "Client not found.";
        }
    }

}
