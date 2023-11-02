package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Meetings;
import map.project.MihaiStupyMAPSpring.data.repository.MeetingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@ShellComponent
public class MeetingsDirectoryCLI {

    @Autowired
    private MeetingsRepository meetingsRepository;

    @ShellMethod(key = "list-meetings", value = "List all meetings")
    public String listAllMeetings() {
        Iterable<Meetings> meetings = meetingsRepository.findAll();
        StringBuilder result = new StringBuilder("List of Meetings:\n");
        meetings.forEach(meeting -> result.append(meeting.getMeetingID()).append(": ").append(meeting.getTitle()).append("\n"));
        return result.toString();
    }

    @ShellMethod(key = "add-meeting", value = "Add a new meeting")
    public String addMeeting(
            @ShellOption(value = "meetingID", help = "Meeting ID") int meetingID,
            @ShellOption(value = "departmentID", help = "Department ID") int departmentID,
            @ShellOption(value = "title", help = "Title") String title,
            @ShellOption(value = "startDate", help = "Start Date (yyyy-MM-dd HH:mm:ss)") String startDateStr,
            @ShellOption(value = "endDate", help = "End Date (yyyy-MM-dd HH:mm:ss)") String endDateStr,
            @ShellOption(value = "location", help = "Location") String location,
            @ShellOption(value = "meetingType", help = "Meeting Type") String meetingType) {

        Department department = new Department();
        department.setDepartmentID(departmentID);

        Date startDate = parseDate(startDateStr);
        Date endDate = parseDate(endDateStr);

        Meetings meeting = new Meetings(meetingID, department, title, startDate, endDate, location, meetingType);
        meetingsRepository.save(meeting);

        return "Meeting added successfully.";
    }

    @ShellMethod(key = "update-meeting", value = "Update a meeting")
    public String updateMeeting(
            @ShellOption(value = "meetingID", help = "Meeting ID") int meetingID,
            @ShellOption(value = "departmentID", help = "Department ID") int departmentID,
            @ShellOption(value = "title", help = "Title") String title,
            @ShellOption(value = "startDate", help = "Start Date (yyyy-MM-dd HH:mm:ss)") String startDateStr,
            @ShellOption(value = "endDate", help = "End Date (yyyy-MM-dd HH:mm:ss)") String endDateStr,
            @ShellOption(value = "location", help = "Location") String location,
            @ShellOption(value = "meetingType", help = "Meeting Type") String meetingType) {

        Meetings existingMeeting = meetingsRepository.findById(meetingID).orElse(null);

        if (existingMeeting == null) {
            return "Meeting not found.";
        }

        Department department = new Department();
        department.setDepartmentID(departmentID);

        Date startDate = parseDate(startDateStr);
        Date endDate = parseDate(endDateStr);

        existingMeeting.setDepartment(department);
        existingMeeting.setTitle(title);
        existingMeeting.setStartDate(startDate);
        existingMeeting.setEndDate(endDate);
        existingMeeting.setLocation(location);
        existingMeeting.setMeetingType(meetingType);

        meetingsRepository.save(existingMeeting);

        return "Meeting updated successfully.";
    }

    @ShellMethod(key = "delete-meeting", value = "Delete a meeting")
    public String deleteMeeting(@ShellOption(value = "meetingID", help = "Meeting ID") int meetingID) {
        Meetings meeting = meetingsRepository.findById(meetingID).orElse(null);

        if (meeting == null) {
            return "Meeting not found.";
        }

        meetingsRepository.delete(meeting);

        return "Meeting deleted successfully.";
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
