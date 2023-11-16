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
    public void getSortedRepo() {
        List<Employee> tempList = new ArrayList<>();
        int index = 0;
        tempList =employeeRepository.findAll();

        int n = tempList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (tempList.get(j).getDepartment().getDepartmentID() > tempList.get(j+1).getDepartment().getDepartmentID()) {
                    // Swap arr[j] and arr[j+1]
                    Collections.swap(tempList,j,j+1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        for(Employee emp : tempList){
            System.out.println("Employee ID: "+emp.getEmployeeID());
            System.out.println("Full Name: "+emp.getFirstName()+" "+emp.getLastName());
            System.out.println("Department ID: "+emp.getDepartment().getDepartmentID());
            System.out.println("Email Address: "+emp.getEmailAddress());
            System.out.println("Phone Number: "+emp.getPhoneNumber());
            System.out.println("");
        }
    }
}
