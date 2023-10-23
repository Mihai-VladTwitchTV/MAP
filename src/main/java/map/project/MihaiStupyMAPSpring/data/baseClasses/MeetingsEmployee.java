package map.project.MihaiStupyMAPSpring.data.baseClasses;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(name = "MeetingsEmployee")
public class MeetingsEmployee {
    @EmbeddedId
    private MeetingsEmployeeId id;

    @ManyToOne
    @MapsId("meetingID")
    @JoinColumn(name = "meetingID")
    private Meetings meeting;

    @ManyToOne
    @MapsId("employeeID")
    @JoinColumn(name = "employeeID")
    private Employee employee;

    private String attendanceStatus;
}

@Embeddable
@Data
class MeetingsEmployeeId implements Serializable {
    private int meetingID;
    private int employeeID;
}
