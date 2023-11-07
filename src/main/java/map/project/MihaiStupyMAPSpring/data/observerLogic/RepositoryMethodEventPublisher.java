package map.project.MihaiStupyMAPSpring.data.observerLogic;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RepositoryMethodEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public RepositoryMethodEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishRepositoryMethodEvent(Object source) {
        eventPublisher.publishEvent(new RepositoryMethodEvent(source));
    }
}

