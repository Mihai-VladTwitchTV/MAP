package map.project.MihaiStupyMAPSpring.service;

import jakarta.transaction.Transactional;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
//import map.project.MihaiStupyMAPSpring.data.repository.this.getRepository();
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService extends BaseService<Client, Integer> {
    

    @Autowired
    public ClientService(ClientRepository repository) {
        super(repository);
    }

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @Transactional
    public void saveWithAssignments(String assignmentName, Client client) {
        Assignments assignment = new Assignments();
        assignment.setAssignmentName(assignmentName);
        assignment.setClient(client);

        // Initialize assignments list if null
        if (client.getAssignments() == null) {
            client.setAssignments(new ArrayList<>());
        }

        // Add the new assignment
        client.getAssignments().add(assignment);

        // Save the assignment
        assignmentsRepository.save(assignment);

        // Save the client
        this.getRepository().save(client);
    }

    public Client findClientById(int clientId) {
        return this.getRepository().findById(clientId).orElseThrow(RuntimeException::new);
    }

    public Client saveClient(Client client) {
        return this.getRepository().save(client);
    }

    @Transactional
    public void deleteClientById(int clientId) {
        this.getRepository().deleteById(clientId);
    }

    @Transactional
    public Client updateClient(int clientId, Client updatedClient) {
        Client existingClient = this.getRepository().findById(clientId).orElseThrow(RuntimeException::new);

        if (existingClient != null) {
            // Update the existing client with the new values
            existingClient.setFirstName(updatedClient.getFirstName());
            existingClient.setLastName(updatedClient.getLastName());
            existingClient.setEmailAddress(updatedClient.getEmailAddress());
            existingClient.setPhoneNumber(updatedClient.getPhoneNumber());

            // Save the updated client
            return this.getRepository().save(existingClient);
        }

        // Handle the case where the client with the given ID is not found
        return null;
    }
    
    public List<Client> findAllClients() {
        return this.getRepository().findAll();
    }
}