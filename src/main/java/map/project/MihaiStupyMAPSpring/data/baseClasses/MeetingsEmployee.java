package map.project.MihaiStupyMAPSpring.data.baseClasses;

import map.project.MihaiStupyMAPSpring.data.baseClasses.MeetingsEmployeeId;
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


