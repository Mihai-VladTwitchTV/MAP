package map.project.MihaiStupyMAPSpring.data.observerLogic;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RepositoryObserver {

    @EventListener
    public void handleRepositoryMethodEvent(RepositoryMethodEvent event) {
        // Implement your observer logic here
        Object source = event.getSource();
        // Perform actions based on the event
    }
}

