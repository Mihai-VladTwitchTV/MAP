package map.project.MihaiStupyMAPSpring.baseClasses;

public class DepartmentLeader extends Employee implements lead {


    @Override
    public void doLeaderStuff() {
        System.out.println("Im a leader");

    }
}
