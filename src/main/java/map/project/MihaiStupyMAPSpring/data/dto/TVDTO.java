package map.project.MihaiStupyMAPSpring.data.dto;

import map.project.MihaiStupyMAPSpring.data.baseClasses.TV;

public class TVDTO {
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

    public TV toTV() {
        TV tv = new TV();
        tv.setId(this.id);
        tv.setBrandName(this.brandName);
        return tv;
    }

}
