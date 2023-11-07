package map.project.MihaiStupyMAPSpring.data.sorterLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class SorterCommands {
    @Autowired
    private Sorter sorter;


    public void setSortRepo(Sorter sorter){
        this.sorter = sorter;
    }

    public void getSortedEntities(){

    }
}
