package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Employee;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Meetings;
import map.project.MihaiStupyMAPSpring.data.baseClasses.MeetingsEmployee;

import map.project.MihaiStupyMAPSpring.data.baseClasses.MeetingsEmployeeId;
import map.project.MihaiStupyMAPSpring.data.repository.EmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.repository.MeetingsEmployeeRepository;
import map.project.MihaiStupyMAPSpring.data.repository.MeetingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MeetingsEmployeeMTMDirectoryCLI implements CommandLineRunner {

    @Autowired
    private MeetingsEmployeeRepository meetingsEmployeeRepository;

    @Autowired
    private MeetingsRepository meetingsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MeetingsEmployeeMTMDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Meetings Employee Management CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all meeting attendees");
            System.out.println("2. Add an employee to a meeting");
            System.out.println("3. Remove an employee from a meeting");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listMeetingAttendees(scanner);
                    break;
                case 2:
                    addEmployeeToMeeting(scanner);
                    break;
                case 3:
                    removeEmployeeFromMeeting(scanner);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listMeetingAttendees(Scanner scanner) {
        System.out.print("Enter the Meeting ID: ");
        int meetingID = scanner.nextInt();

        Meetings meeting = meetingsRepository.findById(meetingID).orElse(null);

        if (meeting != null) {
            Iterable<MeetingsEmployee> meetingAttendees = meetingsEmployeeRepository.findAllByMeeting(meeting);
            System.out.println("Attendees of Meeting " + meetingID + ":");
            meetingAttendees.forEach(meetingEmployee -> {
                System.out.println("Employee ID: " + meetingEmployee.getEmployee().getEmployeeID() + ", Name: " + meetingEmployee.getEmployee().getFirstName() + " " + meetingEmployee.getEmployee().getLastName());
            });
        } else {
            System.out.println("Meeting with ID " + meetingID + " not found.");
        }
    }

    private void addEmployeeToMeeting(Scanner scanner) {
        System.out.print("Enter the Meeting ID: ");
        int meetingID = scanner.nextInt();
        Meetings meeting = meetingsRepository.findById(meetingID).orElse(null);

        System.out.print("Enter the Employee ID to add: ");
        int employeeID = scanner.nextInt();
        Employee employee = employeeRepository.findById(employeeID).orElse(null);

        if (meeting != null && employee != null) {
            MeetingsEmployeeId id = new MeetingsEmployeeId(meetingID, employeeID);
            MeetingsEmployee meetingsEmployee = new MeetingsEmployee();
            meetingsEmployee.setId(id);
            meetingsEmployee.setMeeting(meeting);
            meetingsEmployee.setEmployee(employee);
            meetingsEmployee.setAttendanceStatus("Present");

            meetingsEmployeeRepository.save(meetingsEmployee);
            System.out.println("Employee added to the meeting.");
        } else {
            System.out.println("Meeting or employee not found.");
        }
    }

    private void removeEmployeeFromMeeting(Scanner scanner) {
        System.out.print("Enter the Meeting ID: ");
        int meetingID = scanner.nextInt();
        Meetings meeting = meetingsRepository.findById(meetingID).orElse(null);

        System.out.print("Enter the Employee ID to remove: ");
        int employeeID = scanner.nextInt();
        Employee employee = employeeRepository.findById(employeeID).orElse(null);

        if (meeting != null && employee != null) {
            MeetingsEmployeeId id = new MeetingsEmployeeId(meetingID, employeeID);
            meetingsEmployeeRepository.deleteById(id);
            System.out.println("Employee removed from the meeting.");
        } else {
            System.out.println("Meeting or employee not found.");
        }
    }
}
