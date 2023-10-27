package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<T, ID> {
    private final JpaRepository<T, ID> repository;

    public BaseService(JpaRepository repository) {
        this.repository = (JpaRepository<T, ID>) repository;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    // Add more template methods here
}

