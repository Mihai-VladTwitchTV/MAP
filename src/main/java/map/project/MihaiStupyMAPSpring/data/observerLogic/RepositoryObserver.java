package map.project.MihaiStupyMAPSpring.data.observerLogic;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryObserver {

    private List<String> observedEvents = new ArrayList<>();

    @EventListener
    public void handleRepositoryMethodEvent(RepositoryMethodEvent event) {
        Object source = event.getSource();

        String marker = "Repository method called with source: " + source;

        observedEvents.add(marker);
    }

    public List<String> getObservedEvents() {
        return observedEvents;
    }
}

