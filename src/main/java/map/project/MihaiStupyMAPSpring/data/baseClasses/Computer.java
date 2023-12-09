package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Computer")
public class Computer {
    public Computer(int id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public Computer() {
    }

    @Setter
    @Getter
    @Id
    private int id;

    @Setter
    @Getter
    @Column(name = "brandName", nullable = false)
    private String brandName;


    public String connect(Monitor monitor){
        return "Computer with ID "+this.id+" and brand "+this.brandName+" connected to "+monitor.turnOn();
    }
}
