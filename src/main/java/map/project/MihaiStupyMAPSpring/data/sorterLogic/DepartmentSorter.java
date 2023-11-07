package map.project.MihaiStupyMAPSpring.data.sorterLogic;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentSorter implements Sorter{///Strategy Pattern

    private Sorter sorter;
    private DepartmentRepository deprep;
    @Override
    public void getSortedRepo() {
        int[] idArray = new int[deprep.findAll().size()];
        int index = 0;
        for(Department dep : deprep.findAll()){
            idArray[index] = dep.getDepartmentID();
            index++;
        }
        int n = idArray.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (idArray[j] > idArray[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = idArray[j];
                    idArray[j] = idArray[j + 1];
                    idArray[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped in the inner loop, the array is already sorted
            if (!swapped) {
                break;
            }
        }
        List<Department> sortedDepList = new ArrayList<>();
        for(Integer id : idArray){
            System.out.println(id);
        }
    }
}
