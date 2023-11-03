package map.project.MihaiStupyMAPSpring.data.repository;

import map.project.MihaiStupyMAPSpring.data.baseClasses.DepartmentLeader;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.FullTimeEmployee;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Integer> {
    Iterable<FullTimeEmployee> findAllByIsFullTime(boolean yn);

    Iterable<Employee> findAllByIsPartTime(boolean yn);

    Iterable<DepartmentLeader> findAllByIsDepartmentLeader(boolean yn);
    // Add specific repository methods here, if needed
}


