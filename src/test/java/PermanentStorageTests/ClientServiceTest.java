package PermanentStorageTests;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import map.project.MihaiStupyMAPSpring.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Spy
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clientService = new ClientService(clientRepository);
    }

    @Test
    public void testFindClientById() {
        int clientId = 1;
        Client client = new Client(clientId, "John", "Doe", "john.doe@example.com", 123456);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of((client)));

        Client result = clientService.findClientById(clientId);

        assertEquals(client, result);
    }

    @Test
    public void testFindNonExistentClientById() {
        int clientId = 2;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,()->clientService.findClientById(clientId));
    }

    @Test
    public void testUpdateClient() {
        Client existingClient = new Client(1, "John", "Doe", "john.doe@example.com", 123456);
        when(clientRepository.findById(anyInt())).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(any())).thenReturn(existingClient);

        Client updatedClient = clientService.updateClient(1, new Client(1, "Updated", "Doe", "updated.doe@example.com", 789012));

        assertEquals(existingClient, updatedClient);
        verify(clientRepository, times(1)).findById(anyInt());
        verify(clientRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateNonExistentClient() {
        Client nonExistentClient = new Client(2, "Jane", "Doe", "jane.doe@example.com", 345678);

        assertThrows(RuntimeException.class,()->clientService.updateClient(3, nonExistentClient));

    }

    @Test
    public void testDeleteClient() {
        int clientId = 1;
        Client existingClient = new Client(clientId, "John", "Doe", "john.doe@example.com", 123456);
        when(clientRepository.findById(anyInt())).thenReturn(Optional.of(existingClient));

        clientService.deleteClientById(clientId);

        verify(clientRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void testDeleteNonExistentClient() {
        int nonExistentClientId = 2;

        clientService.deleteClientById(nonExistentClientId);

        verify(clientRepository).deleteById(anyInt());
    }

    @Test
    public void testFindAllClients() {
        List<Client> clientList = Collections.singletonList(new Client(1, "John", "Doe", "john.doe@example.com", 123456));
        when(clientRepository.findAll()).thenReturn(clientList);

        List<Client> result = clientService.findAllClients();

        assertEquals(clientList, result);
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllClientsEmptyList() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        List<Client> result = clientService.findAllClients();

        assertTrue(result.isEmpty());
        verify(clientRepository, times(1)).findAll();
    }
}
