package map.project.MihaiStupyMAPSpring.data.sorterLogic;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.toArray;

@Component
public class EmployeeSorter implements Sorter{///Strategy Pattern


    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getSortedRepo() {
        List<Employee> tempList = new ArrayList<>(employeeRepository.findAll());

        int n = tempList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (tempList.get(j).getEmployeeID() > tempList.get(j + 1).getEmployeeID()) {
                    // Swap arr[j] and arr[j+1]
                    Collections.swap(tempList, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return tempList;
    }
}
