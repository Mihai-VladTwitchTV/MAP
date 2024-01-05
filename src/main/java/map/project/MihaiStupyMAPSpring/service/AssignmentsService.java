package map.project.MihaiStupyMAPSpring.service;

import jakarta.transaction.Transactional;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Assignments;
import map.project.MihaiStupyMAPSpring.data.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentsService extends BaseService<Assignments, Integer> {
    private final AssignmentsRepository assignmentsRepository;

    @Autowired
    public AssignmentsService(AssignmentsRepository repository) {
        super(repository);
        this.assignmentsRepository = repository;
    }
    @Transactional
    public Assignments update(int id, Assignments updatedAssignment) {
        Assignments existingAssignment = assignmentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        // Update the attributes
        existingAssignment.setAssignmentName(updatedAssignment.getAssignmentName());
        existingAssignment.setProject(updatedAssignment.getProject());
        // Note: Handling of employees set should be done carefully
        // depending on business logic

        return assignmentsRepository.save(existingAssignment);
    }

    // Add specific service methods for Assignments, if needed
}