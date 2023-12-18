package map.project.MihaiStupyMAPSpring.Controller;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Computer;
import map.project.MihaiStupyMAPSpring.data.dto.ComputerDTO;
import map.project.MihaiStupyMAPSpring.data.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    @Autowired
    private ComputerRepository computerRepository;

    @GetMapping()
    public List<Computer> listComputers() {
        return computerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Computer> findComputer(@PathVariable int id) {
        return computerRepository.findById(id);
    }

    @PostMapping()
    public Computer addComputer(@RequestBody ComputerDTO request) {
        return computerRepository.save(request.toComputer());
    }

    @DeleteMapping("/{id}")
    public void deleteCompute(@PathVariable int id) {
        computerRepository.deleteById(id);
    }
    // Additional methods for update and delete
}
