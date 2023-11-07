package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Meetings;
import map.project.MihaiStupyMAPSpring.data.baseClasses.MeetingsEmployee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.MeetingsEmployeeId;
import map.project.MihaiStupyMAPSpring.data.observerLogic.RepositoryMethodEventPublisher;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.repository.MeetingsEmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.repository.MeetingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@ShellComponent
public class MeetingsEmployeeMTMDirectoryCLI {

    @Autowired
    private MeetingsEmployeeRepository meetingsEmployeeRepository;

    @Autowired
    private MeetingsRepository meetingsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RepositoryMethodEventPublisher eventPublisher;

    @ShellMethod(key = "list-meeting-attendees", value = "List meeting attendees")
    public String listMeetingAttendees(@ShellOption(value = "meetingID", help = "Meeting ID") int meetingID) {
        eventPublisher.publishRepositoryMethodEvent(this);
        Meetings meeting = meetingsRepository.findById(meetingID).orElse(null);

        if (meeting != null) {
            eventPublisher.publishRepositoryMethodEvent(this);
            Iterable<MeetingsEmployee> meetingAttendees = meetingsEmployeeRepository.findAllByMeeting(meeting);
            StringBuilder result = new StringBuilder("Attendees of Meeting " + meetingID + ":\n");
            meetingAttendees.forEach(meetingEmployee -> {
                Employee employee = meetingEmployee.getEmployee();
                result.append("Employee ID: ").append(employee.getEmployeeID()).append(", Name: ")
                        .append(employee.getFirstName()).append(" ").append(employee.getLastName()).append("\n");
            });
            return result.toString();
        } else {
            return "Meeting with ID " + meetingID + " not found.";
        }
    }

    @ShellMethod(key = "add-employee-to-meeting", value = "Add an employee to a meeting")
    public String addEmployeeToMeeting(
            @ShellOption(value = "meetingID", help = "Meeting ID") int meetingID,
            @ShellOption(value = "employeeID", help = "Employee ID") int employeeID) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Meetings meeting = meetingsRepository.findById(meetingID).orElse(null);
        eventPublisher.publishRepositoryMethodEvent(this);
        Employee employee = employeeRepository.findById(employeeID).orElse(null);

        if (meeting != null && employee != null) {
            MeetingsEmployeeId id = new MeetingsEmployeeId(meetingID, employeeID);
            MeetingsEmployee meetingsEmployee = new MeetingsEmployee();
            meetingsEmployee.setId(id);
            meetingsEmployee.setMeeting(meeting);
            meetingsEmployee.setEmployee(employee);
            meetingsEmployee.setAttendanceStatus("Present");

            eventPublisher.publishRepositoryMethodEvent(this);
            meetingsEmployeeRepository.save(meetingsEmployee);
            return "Employee added to the meeting.";
        } else {
            return "Meeting or employee not found.";
        }
    }

    @ShellMethod(key = "remove-employee-from-meeting", value = "Remove an employee from a meeting")
    public String removeEmployeeFromMeeting(
            @ShellOption(value = "meetingID", help = "Meeting ID") int meetingID,
            @ShellOption(value = "employeeID", help = "Employee ID") int employeeID) {

        eventPublisher.publishRepositoryMethodEvent(this);
        Meetings meeting = meetingsRepository.findById(meetingID).orElse(null);
        eventPublisher.publishRepositoryMethodEvent(this);
        Employee employee = employeeRepository.findById(employeeID).orElse(null);

        if (meeting != null && employee != null) {
            MeetingsEmployeeId id = new MeetingsEmployeeId(meetingID, employeeID);
            eventPublisher.publishRepositoryMethodEvent(this);
            meetingsEmployeeRepository.deleteById(id);
            return "Employee removed from the meeting.";
        } else {
            return "Meeting or employee not found.";
        }
    }
}
