package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends BaseService<Employee, Integer> {
    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }

    // Add specific service methods for Employee, if needed
}
