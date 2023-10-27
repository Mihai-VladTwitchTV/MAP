package map.project.MihaiStupyMAPSpring.data.repository;
import map.project.MihaiStupyMAPSpring.data.baseClasses.PartTimeEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTimeEmployeeRepository extends BaseRepository<PartTimeEmployee, Integer> {
    // Add specific repository methods here, if needed
}
