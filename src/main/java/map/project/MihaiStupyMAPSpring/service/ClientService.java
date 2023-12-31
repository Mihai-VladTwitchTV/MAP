package map.project.MihaiStupyMAPSpring.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Project;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
//import map.project.MihaiStupyMAPSpring.data.repository.this.getRepository();
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService extends BaseService<Client, Integer> {
    

    @Autowired
    public ClientService(ClientRepository repository,DepartmentService departmentService,ProjectRepository projectRepository) {
        super(repository);
        this.departmentService=departmentService;
        this.projectRepository=projectRepository;
    }


    private ProjectRepository projectRepository;

    private DepartmentService departmentService;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public void saveWithProject(
            String projectName, Client client, int projectID, String status,
            Date startDate, Date endDate, int departmentID, String meetingType) {

        // Retrieve the department by name (assuming you have a department service)
        Department department = departmentService.findDepartmentById(departmentID);

        Project project = new Project();
        project.setProjectName(projectName);
        project.setClient(client);
        project.setProjectID(projectID);
        project.setStatus(status);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setMeetingType(meetingType);
        project.setDepartment(department);

        // Initialize projects list if null
        if (client.getProjects() == null) {
            client.setProjects(new ArrayList<>());
        }

        // Add the new project
        client.getProjects().add(project);

        // Save the project
        projectRepository.save(project);

        // Save the client
        clientRepository.save(client);
    }

    public Client findClientById(int clientId) {
        return  clientRepository.findById(clientId).orElseThrow(RuntimeException::new);
    }

    public Client saveClient(Client client) {
        return  clientRepository.save(client);
    }

    @Transactional
    public void deleteClientById(int clientId) {
        clientRepository.deleteById(clientId);
    }

    @Transactional
    public Client updateClient(int clientId, Client updatedClient) {
        Client existingClient =  clientRepository.findById(clientId).orElseThrow(RuntimeException::new);

        if (existingClient != null) {
            // Update the existing client with the new values
            existingClient.setFirstName(updatedClient.getFirstName());
            existingClient.setLastName(updatedClient.getLastName());
            existingClient.setEmailAddress(updatedClient.getEmailAddress());
            existingClient.setPhoneNumber(updatedClient.getPhoneNumber());

            // Save the updated client
            return  clientRepository.save(existingClient);
        }

        // Handle the case where the client with the given ID is not found
        return null;
    }
    
    public List<Client> findAllClients() {
        return  clientRepository.findAll();
    }
    public Client findByFirstName(String firstName) {
        return clientRepository.findAll()
                .stream()
                .filter(client -> client.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
    }

    public List<Client> findByLastName(String lastName) {
        return  clientRepository.findAll()
                .stream()
                .filter(client -> client.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    public List<Client> findByNameContaining(String name) {
        return  clientRepository.findByFirstNameContainingOrLastNameContaining(name, name);
    }

    protected ClientRepository getRepository() {
        return clientRepository;
    }
}