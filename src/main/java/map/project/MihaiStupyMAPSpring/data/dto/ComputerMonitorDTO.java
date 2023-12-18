package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.ComputerMonitor;

public class ComputerMonitorDTO {
    private int id;
    private String brandName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public ComputerMonitor toComputerMonitor() {
        ComputerMonitor computerMonitor = new ComputerMonitor();
        computerMonitor.setId(this.id);
        computerMonitor.setBrandName(this.brandName);
        return computerMonitor;
    }

}
