package map.project.MihaiStupyMAPSpring.data.baseClasses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Entity
@Table(name = "TV")
public class TV{

    public TV(int id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public TV(){

    }

    @Setter
    @Getter
    @Id
    private int id;

    @Setter
    @Getter
    @Column(name = "brandName", nullable = false)
    private String brandName;


    public String turnOn() {
        return "TV with ID " + this.id+" and brand "+this.brandName;
    }
}
