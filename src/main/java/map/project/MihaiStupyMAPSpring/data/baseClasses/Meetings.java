package map.project.MihaiStupyMAPSpring.data.baseClasses;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Entity
@Table(name = "Meetings")
public class Meetings {
    public Meetings() {
    }

    public Meetings(int meetingID, Department department, String title, Date startDate, Date endDate, String location, String meetingType) {
        this.meetingID = meetingID;
        this.department = department;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.meetingType = meetingType;
    }

    @Setter
    @Getter
    @Id
    private int meetingID;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "departmentID")
    private Department department;



    @Setter
    @Getter
    @Column(name = "title")
    private String title;

    @Setter
    @Getter
    @Column(name = "startDate")
    private Date startDate;

    @Setter
    @Getter
    @Column(name = "endDate")
    private Date endDate;

    @Setter
    @Getter
    @Column(name = "location")
    private String location;

    @Setter
    @Getter
    @Column(name = "meetingType")
    private String meetingType;
}
