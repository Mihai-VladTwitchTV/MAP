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
    public ExtendedComputerDecorator(int id, Computer computer, int connectorId,String connectorType,int latency) {
        this.id = id;
        this.computer = computer;
        this.connector = new Connector(id,connectorType,latency);
    }



    @Getter
    @Setter
    @Id
    private int id;


    @OneToOne
    @JoinColumn(name = "computer", nullable = false)
    private Computer computer;


    @OneToOne
    @JoinColumn(name = "connector", nullable = false)
    private Connector connector;

    public ExtendedComputerDecorator() {

    }


    @Override
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public String getConnectorType(){
        return this.connector.getType();
    }

    @Override
    public int getLatency(){
        return this.connector.getLatency();
    }

    @Override
    public int getConnectorId(){
        return this.connector.getId();
    }

    @Override
    public Computer getComputer() {
        return this.computer;
    }

    @Override
    public void setConnector(int id,String type,int latency){
        this.connector = new Connector(id,type,latency);
    }





    @Data
    @Entity
    @Table(name = "Connector")
    private class Connector{

        @Getter
        @Setter
        @Id
        private int id;
        public Connector(int id,String type, int latency) {
            this.id = id;
            this.type = type;
            this.latency = latency;
        }


        @Getter
        @Setter
        @Column(name = "type")
        private String type;

        @Getter
        @Setter
        @Column(name = "latency")
        private int latency;


        public Connector() {

        }
    }
}


