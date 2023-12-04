package PermanentStorageTests;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import map.project.MihaiStupyMAPSpring.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
@Transactional
public class ClientIntegrationTest {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @Autowired
    private ClientService clientService;

    @Test
    public void testFindClientById() throws Exception {
        Client existingClient = clientRepository.findById(2).orElse(null);

        assert existingClient != null;
        Client foundClient = clientService.findById(existingClient.getClientID());

        assertEquals(existingClient, foundClient, "Found client should match the existing client");
    }

    @Test
    public void testListAllClients() throws Exception {
        List<Client> existingClients = (List<Client>) clientRepository.findAll();

        List<Client> foundClients = clientService.findAll();

        assertEquals(existingClients.size(), foundClients.size(), "Number of clients should match");
        assertEquals(existingClients, foundClients, "Found clients should match the existing clients");
    }

}
