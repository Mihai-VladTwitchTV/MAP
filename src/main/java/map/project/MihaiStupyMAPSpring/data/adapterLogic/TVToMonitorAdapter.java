package map.project.MihaiStupyMAPSpring.data.adapterLogic;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Monitor;
import map.project.MihaiStupyMAPSpring.data.baseClasses.TV;

import java.util.Set;

@Data
@Entity
@Table(name = "TVToMonitorAdapter")
public class TVToMonitorAdapter implements Monitor {

    public TVToMonitorAdapter(int id,TV tv) {
        this.tv = tv;
        this.id = id;
    }

    public TVToMonitorAdapter(){

    }

    @OneToOne
    @JoinColumn(name = "tv", nullable = false)
    private TV tv;

    @Setter
    @Getter
    @Id
    private int id;


    @Override
    public String turnOn() {
        return tv.turnOn()+" through adapter with id "+this.id;
    }
}
