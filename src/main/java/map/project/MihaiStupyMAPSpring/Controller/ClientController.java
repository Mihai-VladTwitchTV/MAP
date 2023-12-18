package map.project.MihaiStupyMAPSpring.Controller;
import java.util.List;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import map.project.MihaiStupyMAPSpring.data.dto.ClientDTO;
import map.project.MihaiStupyMAPSpring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> listClients() {
        return clientService.findAll();
    }

    @PostMapping()
    public Client addClient(@RequestBody ClientDTO request) {
        Client client = new Client();
        client.setClientID(request.getClientID());
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setEmailAddress(request.getEmailAddress());
        client.setPhoneNumber(request.getPhoneNumber());

        // You can add any other necessary properties here

        return clientService.save(client);
    }


    @GetMapping("/{name}")
    public Client findClient(@PathVariable String name) {
        return (Client) clientService.findByFirstName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClientById(id);
    }

    @GetMapping("/like/{name}")
    public List<Client> findClientLike(@PathVariable String name) {
        return clientService.findByNameContaining(name);
    }



}
