package map.project.MihaiStupyMAPSpring.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public abstract class BaseService<T, ID> {
    private final JpaRepository<T, ID> repository;

    public BaseService(JpaRepository repository) {
        this.repository = (JpaRepository<T, ID>) repository;
    }

    protected JpaRepository<T, ID> getRepository() {
        return repository;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T findById(Integer id) {
        return repository.findById((ID) id).orElse(null);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    // Add more template methods here
}
