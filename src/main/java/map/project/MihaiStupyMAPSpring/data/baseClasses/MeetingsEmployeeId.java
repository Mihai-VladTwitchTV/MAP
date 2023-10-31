package map.project.MihaiStupyMAPSpring.data.baseClasses;

import lombok.Data;

import java.io.Serializable;

@Data
public class MeetingsEmployeeId implements Serializable {
    private int meetingID;
    private int employeeID;

    public MeetingsEmployeeId() {
    }

    public MeetingsEmployeeId(int meetingID, int employeeID) {
        this.meetingID = meetingID;
        this.employeeID = employeeID;
    }
}
