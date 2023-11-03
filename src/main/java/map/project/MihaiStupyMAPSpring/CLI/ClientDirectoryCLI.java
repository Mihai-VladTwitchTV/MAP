package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@ShellComponent
public class ClientDirectoryCLI {

    @Autowired
    private ClientRepository clientRepository;

    @ShellMethod(key = "list-clients", value = "List all clients")
    public String listAllClients() {
        StringBuilder result = new StringBuilder("List of Clients:\n");
        Iterable<Client> clients = clientRepository.findAll();
        clients.forEach(client -> result.append(client.getClientID()).append(": ").append(client.getFirstName()).append(" ").append(client.getLastName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-client", value = "Add a new client")
    public String addClient(@ShellOption({"-id", "--clientID"}) int clientID, @ShellOption({"-first", "--firstName"}) String firstName, @ShellOption({"-last", "--lastName"}) String lastName, @ShellOption({"-email", "--emailAddress"}) String emailAddress, @ShellOption({"-phone", "--phoneNumber"}) int phoneNumber) {
        Client client = new Client(clientID, firstName, lastName, emailAddress, phoneNumber);
        clientRepository.save(client);
        return "Client added successfully.";
    }

    @ShellMethod(key = "update-client", value = "Update a client")
    public String updateClient(@ShellOption({"-id", "--clientID"}) int clientID, @ShellOption({"-first", "--firstName"}) String firstName, @ShellOption({"-last", "--lastName"}) String lastName, @ShellOption({"-email", "--emailAddress"}) String emailAddress, @ShellOption({"-phone", "--phoneNumber"}) int phoneNumber) {
        Client client = clientRepository.findById(clientID).orElse(null);
        if (client != null) {
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setEmailAddress(emailAddress);
            client.setPhoneNumber(phoneNumber);
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
            clientRepository.delete(client);
            return "Client deleted successfully.";
        } else {
            return "Client not found.";
        }
    }
}
