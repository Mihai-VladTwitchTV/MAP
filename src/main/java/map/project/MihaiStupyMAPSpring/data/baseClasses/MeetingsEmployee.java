package map.project.MihaiStupyMAPSpring.data.baseClasses;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Data
@Entity
@Table(name = "MeetingsEmployee")
public class MeetingsEmployee {
    @Setter
    @Getter
    @EmbeddedId
    private MeetingsEmployeeId id;

    @Setter
    @Getter
    @ManyToOne
    @MapsId("meetingID")
    @JoinColumn(name = "meetingID")
    private Meetings meeting;

    @Setter
    @Getter
    @ManyToOne
    @MapsId("employeeID")
    @JoinColumn(name = "employeeID")
    private Employee employee;


    @Setter
    @Getter
    @Column(name = "attendanceStatus")
    private String attendanceStatus;
}

@Embeddable
@Data
class MeetingsEmployeeId implements Serializable {
    public MeetingsEmployeeId() {
    }

    public MeetingsEmployeeId(int meetingID, int employeeID) {
        this.meetingID = meetingID;
        this.employeeID = employeeID;
    }

    @Setter
    @Getter
    private int meetingID;

    @Getter
    @Setter
    private int employeeID;
}
