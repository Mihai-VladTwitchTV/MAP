package map.project.MihaiStupyMAPSpring.data.repository;

import jakarta.transaction.Transactional;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends BaseRepository<Client, Integer> {
    Optional<Client> findByFirstName(String firstName);
    List<Client> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    // Add specific repository methods here, if needed
}

