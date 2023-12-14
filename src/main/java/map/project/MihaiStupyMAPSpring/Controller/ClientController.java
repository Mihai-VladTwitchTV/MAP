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
    public List<Client> listUsers() {
        return clientService.findAll();
    }

    @PostMapping()
    public Client addUser(@RequestBody ClientDTO request) {
        Client client = new Client();
        client.setFirstName(request.getName());
        // Set other properties of Client as necessary
        return clientService.save(client);
    }

    @GetMapping("/{name}")
    public Client findUser(@PathVariable String name) {
        return (Client) clientService.findByFirstName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable int id) {
        clientService.deleteClientById(id);
    }

    @GetMapping("/like/{name}")
    public List<Client> findUserLike(@PathVariable String name) {
        return clientService.findByNameContaining(name);
    }



}
