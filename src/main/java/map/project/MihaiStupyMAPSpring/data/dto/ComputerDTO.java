package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Computer;

public class ComputerDTO {
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
    public Computer toComputer() {
        Computer computer = new Computer();
        computer.setId(this.id);
        computer.setBrandName(this.brandName);
        return computer;
    }

}
