package map.project.MihaiStupyMAPSpring.data.observerLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class RepositoryObserverCommands {

    @Autowired
    private RepositoryObserver observer;

    @ShellMethod(key="observer-print-events",value = "Observer print events")
    public void printObservedEvents() {
        for (String event : observer.getObservedEvents()) {
            System.out.println(event);
        }
    }
}

