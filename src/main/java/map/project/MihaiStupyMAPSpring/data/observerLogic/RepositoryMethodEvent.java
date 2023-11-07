package map.project.MihaiStupyMAPSpring.data.observerLogic;
import org.springframework.context.ApplicationEvent;

public class RepositoryMethodEvent extends ApplicationEvent {
    public RepositoryMethodEvent(Object source) {
        super(source);
    }
}
