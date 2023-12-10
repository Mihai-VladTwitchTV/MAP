package map.project.MihaiStupyMAPSpring.data.decoratorLogic;

import map.project.MihaiStupyMAPSpring.data.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecoratorRepository extends BaseRepository<ExtendedComputerDecorator,Integer> {
}
