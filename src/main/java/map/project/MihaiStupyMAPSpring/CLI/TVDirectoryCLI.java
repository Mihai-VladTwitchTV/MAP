package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.TV;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.TVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class TVDirectoryCLI {

    @Autowired
    private TVRepository tvRepository;


    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @Override
    public String toString() {
        return "TV CLI";
    }

    @ShellMethod(key = "list-tvs", value = "List all TVs")
    public String listAllTVs() {
        StringBuilder result = new StringBuilder("List of TVs:\n");
        Iterable<TV> tvs = tvRepository.findAll();
        eventPublisher.publishRepositoryMethodEvent(this);
        tvs.forEach(tv -> result.append(tv.getId()).append(": ").append(tv.getBrandName()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-tv", value = "Add a new TV")
    public String addTV(
            @ShellOption({"-id", "--tvId"}) int tvId,
            @ShellOption({"-name", "--brandName"}) String brandName
    ) {
        TV tv = new TV(tvId, brandName);

        eventPublisher.publishRepositoryMethodEvent(this);
        tvRepository.save(tv);

        return "TV added successfully.";
    }

    @ShellMethod(key = "update-tv", value = "Update a TV")
    public String updateTV(
            @ShellOption({"-id", "--tvId"}) int tvId,
            @ShellOption({"-name", "--brandName"}) String brandName
    ) {
        eventPublisher.publishRepositoryMethodEvent(this);
        TV tv = tvRepository.findById(tvId).orElse(null);
        if (tv != null) {
            tv.setBrandName(brandName);
            eventPublisher.publishRepositoryMethodEvent(this);
            tvRepository.save(tv);
            return "TV updated successfully.";
        } else {
            return "TV not found.";
        }
    }

    @ShellMethod(key = "delete-tv", value = "Delete a TV")
    public String deleteTV(@ShellOption({"-id", "--tvId"}) int tvId) {
        TV tv = tvRepository.findById(tvId).orElse(null);
        if (tv != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            tvRepository.delete(tv);
            return "TV deleted successfully.";
        } else {
            return "TV not found.";
        }
    }
}
