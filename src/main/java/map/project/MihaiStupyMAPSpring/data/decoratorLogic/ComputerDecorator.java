package map.project.MihaiStupyMAPSpring.data.decoratorLogic;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Computer;

public interface ComputerDecorator {

    public void setComputer(Computer computer);
    public String getConnectorType();

    public int getConnectorId();
    public int getLatency();
    public Computer getComputer();
    public void setConnector(int id,String type, int latency);


}
