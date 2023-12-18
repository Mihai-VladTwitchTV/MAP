package map.project.MihaiStupyMAPSpring.Controller;

import map.project.MihaiStupyMAPSpring.data.baseClasses.ComputerMonitor;
import map.project.MihaiStupyMAPSpring.data.dto.ComputerMonitorDTO;
import map.project.MihaiStupyMAPSpring.data.repository.ComputerMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/computerMonitors")
public class ComputerMonitorController {

    @Autowired
    private ComputerMonitorRepository computerMonitorRepository;

    @GetMapping()
    public List<ComputerMonitor> listComputerMonitors() {
        return computerMonitorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ComputerMonitor> findComputerMonitor(@PathVariable int id) {
        return computerMonitorRepository.findById(id);
    }

    @PostMapping()
    public ComputerMonitor addComputerMonitor(@RequestBody ComputerMonitorDTO request) {
        return computerMonitorRepository.save(request.toComputerMonitor());
    }

    // Additional methods for update and delete
}
