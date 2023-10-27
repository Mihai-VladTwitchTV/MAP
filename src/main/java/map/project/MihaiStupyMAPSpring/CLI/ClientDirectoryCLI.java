package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ClientDirectoryCLI implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(ClientDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Client Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all clients");
            System.out.println("2. Add a new client");
            System.out.println("3. Update a client");
            System.out.println("4. Delete a client");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllClients();
                    break;
                case 2:
                    addClient(scanner);
                    break;
                case 3:
                    updateClient(scanner);
                    break;
                case 4:
                    deleteClient(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllClients() {
        Iterable<Client> clients = clientRepository.findAll();
        System.out.println("List of Clients:");
        clients.forEach(client -> System.out.println(client.getClientID() + ": " + client.getFirstName() + " " + client.getLastName()));
    }

    private void addClient(Scanner scanner) {
        System.out.println("Enter client details:");

        // Get client details from the user
        System.out.print("Client ID: ");
        int clientID = scanner.nextInt();

        System.out.print("First Name: ");
        String firstName = scanner.next();

        System.out.print("Last Name: ");
        String lastName = scanner.next();

        System.out.print("Email Address: ");
        String emailAddress = scanner.next();

        System.out.print("Phone Number: ");
        int phoneNumber = scanner.nextInt();

        // Create a new client
        Client client = new Client(clientID, firstName, lastName, emailAddress, phoneNumber);

        // Save the client to the database
        clientRepository.save(client);

        System.out.println("Client added successfully.");
    }

    private void updateClient(Scanner scanner) {
        System.out.print("Enter client ID to update: ");
        int clientID = scanner.nextInt();
        Client client = clientRepository.findById(clientID).orElse(null);

        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.println("Enter new client details:");

        System.out.print("First Name: ");
        String firstName = scanner.next();
        client.setFirstName(firstName);

        System.out.print("Last Name: ");
        String lastName = scanner.next();
        client.setLastName(lastName);

        System.out.print("Email Address: ");
        String emailAddress = scanner.next();
        client.setEmailAddress(emailAddress);

        System.out.print("Phone Number: ");
        int phoneNumber = scanner.nextInt();
        client.setPhoneNumber(phoneNumber);

        // Save the updated client to the database
        clientRepository.save(client);

        System.out.println("Client updated successfully.");
    }

    private void deleteClient(Scanner scanner) {
        System.out.print("Enter client ID to delete: ");
        int clientID = scanner.nextInt();
        Client client = clientRepository.findById(clientID).orElse(null);

        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        // Delete the client from the database
        clientRepository.delete(client);

        System.out.println("Client deleted successfully.");
    }
}
