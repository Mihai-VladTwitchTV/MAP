package map.project.MihaiStupyMAPSpring.data.baseClasses;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Meetings")
public class Meetings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meetingID;

    @ManyToOne
    @JoinColumn(name = "departmentID")
    private Department department;

    private String title;
    private Date startDate;
    private Date endDate;
    private String location;
    private String meetingType;
}
