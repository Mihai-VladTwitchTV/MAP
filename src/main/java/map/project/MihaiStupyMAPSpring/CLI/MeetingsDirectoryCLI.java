package map.project.MihaiStupyMAPSpring.CLI;

import map.project.MihaiStupyMAPSpring.data.baseClasses.Department;
import map.project.MihaiStupyMAPSpring.data.baseClasses.Meetings;
import map.project.MihaiStupyMAPSpring.data.repository.MeetingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@SpringBootApplication
public class MeetingsDirectoryCLI implements CommandLineRunner {

    @Autowired
    private MeetingsRepository meetingsRepository;

    public static void main(String[] args) {
        SpringApplication.run(MeetingsDirectoryCLI.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to the Meetings Directory CLI!");
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List all meetings");
            System.out.println("2. Add a new meeting");
            System.out.println("3. Update a meeting");
            System.out.println("4. Delete a meeting");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllMeetings();
                    break;
                case 2:
                    addMeeting(scanner);
                    break;
                case 3:
                    updateMeeting(scanner);
                    break;
                case 4:
                    deleteMeeting(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listAllMeetings() {
        Iterable<Meetings> meetings = meetingsRepository.findAll();
        System.out.println("List of Meetings:");
        meetings.forEach(meeting -> System.out.println(meeting.getMeetingID() + ": " + meeting.getTitle()));
    }

    private void addMeeting(Scanner scanner) {
        System.out.println("Enter meeting details:");

        // Get meeting details from the user
        System.out.print("Meeting ID: ");
        int meetingID = scanner.nextInt();

        Department department = createDepartment(scanner);

        System.out.print("Title: ");
        String title = scanner.next();

        System.out.print("Start Date (yyyy-MM-dd HH:mm:ss): ");
        Date startDate = parseDate(scanner.next());

        System.out.print("End Date (yyyy-MM-dd HH:mm:ss): ");
        Date endDate = parseDate(scanner.next());

        System.out.print("Location: ");
        String location = scanner.next();

        System.out.print("Meeting Type: ");
        String meetingType = scanner.next();

        // Create a new meeting
        Meetings meeting = new Meetings(meetingID, department, title, startDate, endDate, location, meetingType);

        // Save the meeting to the database
        meetingsRepository.save(meeting);

        System.out.println("Meeting added successfully.");
    }

    private void updateMeeting(Scanner scanner) {
        System.out.print("Enter meeting ID to update: ");
        int meetingID = scanner.nextInt();
        Meetings meeting = meetingsRepository.findById(meetingID).orElse(null);

        if (meeting == null) {
            System.out.println("Meeting not found.");
            return;
        }

        System.out.println("Enter new meeting details:");

        Department department = createDepartment(scanner);
        meeting.setDepartment(department);

        System.out.print("Title: ");
        String title = scanner.next();
        meeting.setTitle(title);

        System.out.print("Start Date (yyyy-MM-dd HH:mm:ss): ");
        Date startDate = parseDate(scanner.next());
        meeting.setStartDate(startDate);

        System.out.print("End Date (yyyy-MM-dd HH:mm:ss): ");
        Date endDate = parseDate(scanner.next());
        meeting.setEndDate(endDate);

        System.out.print("Location: ");
        String location = scanner.next();
        meeting.setLocation(location);

        System.out.print("Meeting Type: ");
        String meetingType = scanner.next();
        meeting.setMeetingType(meetingType);

        // Save the updated meeting to the database
        meetingsRepository.save(meeting);

        System.out.println("Meeting updated successfully.");
    }

    private void deleteMeeting(Scanner scanner) {
        System.out.print("Enter meeting ID to delete: ");
        int meetingID = scanner.nextInt();
        Meetings meeting = meetingsRepository.findById(meetingID).orElse(null);

        if (meeting == null) {
            System.out.println("Meeting not found.");
            return;
        }

        // Delete the meeting from the database
        meetingsRepository.delete(meeting);

        System.out.println("Meeting deleted successfully.");
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

    private Department createDepartment(Scanner scanner) {
        System.out.print("Department ID: ");
        int departmentID = scanner.nextInt();

        Department department = new Department();
        department.setDepartmentID(departmentID);

        return department;
    }
}
