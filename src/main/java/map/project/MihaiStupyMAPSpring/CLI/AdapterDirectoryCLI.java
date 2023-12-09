package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.adapterLogic.TVToMonitorAdapter;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.MonitorAdapterRepository;
import map.project.MihaiStupyMAPSpring.data.repository.TVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import map.project.MihaiStupyMAPSpring.data.baseClasses.TV;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class AdapterDirectoryCLI {

    @Autowired
    private MonitorAdapterRepository adapterRepository;


    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @Autowired
    private TVRepository tvRepository;

    @ShellMethod(key = "list-adapters", value = "List all TV to Monitor Adapters")
    public String listAllAdapters() {
        StringBuilder result = new StringBuilder("List of TV to Monitor Adapters:\n");
        Iterable<TVToMonitorAdapter> adapters = adapterRepository.findAll();
        eventPublisher.publishRepositoryMethodEvent(this);
        adapters.forEach(adapter -> result.append(adapter.getId()).append(": ").append("TV ID: ").append(adapter.getTv().getId()).append(", TV Brand: ").append(adapter.getTv().getBrandName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-adapter", value = "Add a new TV to Monitor Adapter")
    public String addAdapter(
            @ShellOption({"-id", "--adapterId"}) int adapterId,
            @ShellOption({"-tvId", "--tvId"}) int tvId
    ) {


        Optional<TV> tv = tvRepository.findById(tvId);
        if(tv.isEmpty())
            return "No TV with inputted Id";


        TVToMonitorAdapter adapter = new TVToMonitorAdapter(adapterId, tv.get());

        eventPublisher.publishRepositoryMethodEvent(this);
        adapterRepository.save(adapter);

        return "TV to Monitor Adapter added successfully.";
    }

    @ShellMethod(key = "update-adapter", value = "Update a TV to Monitor Adapter")
    public String updateAdapter(
            @ShellOption({"-id", "--adapterId"}) int adapterId,
            @ShellOption({"-tvId", "--tvId"}) int tvId,
            @ShellOption({"-tvBrand", "--tvBrand"}) String tvBrand
    ) {
        eventPublisher.publishRepositoryMethodEvent(this);
        TVToMonitorAdapter adapter = adapterRepository.findById(adapterId).orElse(null);
        if (adapter != null) {
            adapter.setTv(new TV(tvId, tvBrand));
            eventPublisher.publishRepositoryMethodEvent(this);
            adapterRepository.save(adapter);
            return "TV to Monitor Adapter updated successfully.";
        } else {
            return "TV to Monitor Adapter not found.";
        }
    }

    @ShellMethod(key = "delete-adapter", value = "Delete a TV to Monitor Adapter")
    public String deleteAdapter(@ShellOption({"-id", "--adapterId"}) int adapterId) {
        TVToMonitorAdapter adapter = adapterRepository.findById(adapterId).orElse(null);
        if (adapter != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            adapterRepository.delete(adapter);
            return "TV to Monitor Adapter deleted successfully.";
        } else {
            return "TV to Monitor Adapter not found.";
        }
    }
}
