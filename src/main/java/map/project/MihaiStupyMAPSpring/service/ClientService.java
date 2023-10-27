package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import map.project.MihaiStupyMAPSpring.data.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService extends BaseService<Client, Integer> {
    @Autowired
    public ClientService(ClientRepository repository) {
        super((AssignmentsRepository) repository);
    }

    // Add specific service methods for Client, if needed
}