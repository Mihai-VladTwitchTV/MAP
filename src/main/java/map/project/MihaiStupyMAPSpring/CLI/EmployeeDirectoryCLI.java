import java.util.Scanner;

public class EmployeeDirectoryCLI {
    private static EmployeeService employeeService = new EmployeeService();
    private static DepartmentService departmentService = new DepartmentService();
    private static ClientService clientService = new ClientService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Employee Directory Console Application");
            System.out.println("1. View Departments");
            System.out.println("2. Add Department");
            System.out.println("3. View Employees");
            System.out.println("4. Add Employee");
            System.out.println("5. View Clients");
            System.out.println("6. Add Client");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewDepartments();
                    break;
                case 2:
                    addDepartment();
                    break;
                case 3:
                    viewEmployees();
                    break;
                case 4:
                    addEmployee();
                    break;
                case 5:
                    viewClients();
                    break;
                case 6:
                    addClient();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewDepartments() {
        System.out.println("Departments:");
        departmentService.getAllDepartments().forEach(department -> System.out.println(department));
    }

    private static void addDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add a new department:");
        System.out.print("Department Name: ");
        String departmentName = scanner.nextLine();
        System.out.print("Manager ID: ");
        int managerID = scanner.nextInt();

        Department newDepartment = new Department(departmentName, managerID);
        departmentService.addDepartment(newDepartment);
        System.out.println("Department added successfully.");
    }

    private static void viewEmployees() {
        System.out.println("Employees:");
        employeeService.getAllEmployees().forEach(employee -> System.out.println(employee));
    }

    private static void addEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add a new employee:");
        System.out.print("Employee Name: ");
        String employeeName = scanner.nextLine();
        System.out.print("Position: ");
        String position = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Department ID: ");
        int departmentID = scanner.nextInt();

        Employee newEmployee = new Employee(employeeName, position, email, phone, departmentID);
        employeeService.addEmployee(newEmployee);
        System.out.println("Employee added successfully.");
    }

    private static void viewClients() {
        System.out.println("Clients:");
        clientService.getAllClients().forEach(client -> System.out.println(client));
    }

    private static void addClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add a new client:");
        System.out.print("Client Name: ");
        String clientName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        Client newClient = new Client(clientName, email, phone);
        clientService.addClient(newClient);
        System.out.println("Client added successfully.");
    }
}
