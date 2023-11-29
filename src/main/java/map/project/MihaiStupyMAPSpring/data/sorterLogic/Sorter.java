package map.project.MihaiStupyMAPSpring.data.sorterLogic;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Sorter<T> {
    List<T> getSortedRepo();
}
