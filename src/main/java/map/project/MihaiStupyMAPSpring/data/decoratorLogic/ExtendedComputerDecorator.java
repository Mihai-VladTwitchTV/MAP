package map.project.MihaiStupyMAPSpring.data.decoratorLogic;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Computer;


@Data
@Entity
@Table(name = "ExtendedComputerDecorator")
public class ExtendedComputerDecorator implements  ComputerDecorator{
    public ExtendedComputerDecorator(int id, Computer computer,String connectorType,int latency) {
        this.id = id;
        this.computer = computer;
        this.connectorType = connectorType;
        this.latency = latency;
    }



    @Getter
    @Setter
    @Id
    private int id;

    @Setter
    @Column(name = "connectorType")
    private String connectorType;

    @Setter
    @Column(name = "latency")
    private int latency;


    @OneToOne
    @JoinColumn(name = "computer", nullable = false)
    private Computer computer;


    public ExtendedComputerDecorator() {

    }


    @Override
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public String getConnectorType(){
        return this.connectorType;
    }

    @Override
    public int getLatency(){
        return this.latency;
    }



    @Override
    public Computer getComputer() {
        return this.computer;
    }

}


