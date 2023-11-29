package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeService extends BaseService<Employee, Integer> {
    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }

    public void delete(int employeeId) {
        getRepository().deleteById(employeeId);
    }

    public Optional<Employee> getById(int employeeId) {
        return getRepository().findById(employeeId);
    }

    public Iterable<Employee> getAll() {
        return getRepository().findAll();
    }
    // Add specific service methods for Employee, if needed
}
