package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Entity
@Table(name = "ComputerMonitor")
public class ComputerMonitor implements Monitor{

    public ComputerMonitor(int id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public ComputerMonitor (){

    }

    @Setter
    @Getter
    @Id
    private int id;

    @Setter
    @Getter
    @Column(name = "brandName", nullable = false)
    private String brandName;

    @Override
    public String turnOn() {
        return "Monitor with ID " + this.id+" and brand "+this.brandName;
    }
}
