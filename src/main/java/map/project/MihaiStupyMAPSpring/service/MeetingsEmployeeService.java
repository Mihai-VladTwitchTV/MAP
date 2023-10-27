package map.project.MihaiStupyMAPSpring.service;

import map.project.MihaiStupyMAPSpring.data.baseClasses.MeetingsEmployee;
import map.project.MihaiStupyMAPSpring.data.repository.MeetingsEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingsEmployeeService extends BaseService<MeetingsEmployee, Integer> {
    @Autowired
    public MeetingsEmployeeService(MeetingsEmployeeRepository repository) {
        super(repository);
    }

    // Add specific service methods for MeetingsEmployee, if needed
}

