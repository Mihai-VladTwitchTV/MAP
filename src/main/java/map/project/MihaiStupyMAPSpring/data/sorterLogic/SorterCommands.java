//package map.project.MihaiStupyMAPSpring.data.sorterLogic;
//
//import map.project.MihaiStupyMAPSpring.data.repository.DepartmentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;
//import org.springframework.stereotype.Component;
//
//@ShellComponent
//@Component
//public class SorterCommands {
//    //@Autowired
//    private Sorter sorter;
//
//    @Autowired
//    private DepartmentSorter depsort;
//    @Autowired
//    private EmployeeSorter empsort;
//
//
//   /// @ShellMethod(key="set-repo",value="Set sorting repo")
//    public void setSortRepo(Sorter sorter){
//        this.sorter = sorter;
//    }
//
//    @ShellMethod(key="get-sorted",value="Get sorted Entities")
//    public void getSortedEntities(){
//        sorter.getSortedRepo();
//    }
//
//    @ShellMethod(key = "pick-sorter", value = "Pick sorter")
//    public void pickSorter(@ShellOption(help = "Specify the sorting option") String option) {
//        switch (option) {
//            case "departments":
//                // Handle the --departments option
//                setSortRepo(depsort);
//                System.out.println("Sorting departments");
//                break;
//            case "employees":
//                // Handle the --employees option
//                System.out.println("Sorting employees");
//                setSortRepo(empsort);
//                break;
//            default:
//                System.out.println("Invalid option: " + option);
//                // Handle the case where an unknown option is provided
//                break;
//        }
//    }
//
//
//}
