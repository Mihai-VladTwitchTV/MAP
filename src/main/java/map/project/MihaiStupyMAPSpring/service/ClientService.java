package map.project.MihaiStupyMAPSpring.service;

import jakarta.transaction.Transactional;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService extends BaseService<Client, Integer> {
    @Autowired
    public ClientService(ClientRepository repository) {
        super((ClientRepository) repository);
    }
    @Autowired
    private ClientRepository clientRepository;

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
        clientRepository.save(client);
    }

    // Add specific service methods for Client, if needed
}