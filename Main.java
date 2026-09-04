import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Employee> employees = new ArrayList<>();
    private static final List<LeaveRequest> requests = new ArrayList<>();
    private static int nextRequestId = 1;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n-- welcome to my console based leave management system --");
                System.out.println("Choose your role: Manager, Employee, or Exit");
                String role = readLine(scanner, "role").trim().toLowerCase();
                if ("manager".equals(role)) {
                    runManagerMenu(scanner);
                } else if ("employee".equals(role)) {
                    runEmployeeMenu(scanner);
                } else if ("exit".equals(role)) {
                    return;
                } else {
                    System.out.println("Please type Manager, Employee, or Exit.");
                }
            }
        } catch (IllegalStateException exception) {
            System.out.println("Session ended: " + exception.getMessage());
        }
    }

    private static void runManagerMenu(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = readLine(scanner, "name");
        System.out.print("Enter your email: ");
        String email = readLine(scanner, "email");
        System.out.print("Enter your password: ");
        String password = readLine(scanner, "password");

        if (!"manager@gmail.com".equals(email) || !"Admin123".equals(password)) {
            System.out.println("Incorrect email or password.");
            return;
        }

        Manager manager;
        try {
            manager = new Manager("MANAGER", name, email);
        } catch (IllegalArgumentException exception) {
            System.out.println("Could not sign in: " + exception.getMessage());
            return;
        }
        System.out.println("Welcome manager " + manager.getFullName() + ".");

        while (true) {
            System.out.println("\nManager menu");
            System.out.println("1. View employees");
            System.out.println("2. View and decide leave requests");
            System.out.println("3. View employee balances");
            System.out.println("4. Back to role selection");
            int option = readInt(scanner, "Choose an option: ");

            switch (option) {
                case 1:
                    manager.viewEmployees(employees);
                    break;
                case 2:
                    manager.viewLeaveRequests(requests);
                    decideLeaveRequest(scanner, manager, requests);
                    break;
                case 3:
                    viewEmployeeBalances(employees);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Please choose a valid option.");
                    break;
            }
        }
    }

    private static void runEmployeeMenu(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = readLine(scanner, "name");
        System.out.print("Enter your email: ");
        String email = readLine(scanner, "email");
        System.out.print("Enter your password: ");
        String password = readLine(scanner, "password");

        if (!"employee@gmail.com".equals(email) || !"Employee123".equals(password)) {
            System.out.println("Incorrect email or password.");
            return;
        }

        Employee employee = findEmployee(email);
        if (employee == null) {
            try {
                employee = new Employee("EMPLOYEE-" + (employees.size() + 1), name, email);
            } catch (IllegalArgumentException exception) {
                System.out.println("Could not sign in: " + exception.getMessage());
                return;
            }
            employees.add(employee);
        }
        System.out.println("Welcome employee " + employee.getFullName() + ".");

        while (true) {
            System.out.println("\nEmployee menu");
            System.out.println("1. Submit leave request");
            System.out.println("2. View my leave requests");
            System.out.println("3. View my leave balance");
            System.out.println("4. Exit");
            int option = readInt(scanner, "Choose an option: ");

            switch (option) {
                case 1:
                    int days = readInt(scanner, "How many days? ");
                    System.out.print("Reason: ");
                    String reason = readLine(scanner, "reason");
                    try {
                        requests.add(employee.submitLeaveRequest(nextRequestId++, days, reason));
                        System.out.println("Leave request submitted for manager review.");
                    } catch (IllegalArgumentException exception) {
                        System.out.println("Could not submit request: " + exception.getMessage());
                    }
                    break;
                case 2:
                    if (requests.isEmpty()) {
                        System.out.println("No leave requests found.");
                    } else {
                        for (LeaveRequest request : requests) {
                            System.out.println(request);
                        }
                    }
                    break;
                case 3:
                    System.out.println(employee.viewLeaveBalance());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Please choose a valid option.");
                    break;
            }
        }
    }

    private static void decideLeaveRequest(Scanner scanner, Manager manager,
            List<LeaveRequest> requests) {
        List<LeaveRequest> pendingRequests = new ArrayList<>();
        for (LeaveRequest request : requests) {
            if (request.getStatus() == LeaveRequest.Status.PENDING) {
                pendingRequests.add(request);
            }
        }
        if (pendingRequests.isEmpty()) {
            return;
        }

        int requestId = readInt(scanner, "Enter pending request ID (0 to cancel): ");
        if (requestId == 0) {
            return;
        }
        for (LeaveRequest request : pendingRequests) {
            if (request.getRequestId() == requestId) {
                System.out.print("Approve or reject (a/r): ");
                String decision = readLine(scanner, "decision").trim().toLowerCase();
                try {
                    if ("a".equals(decision)) {
                        manager.approveLeaveRequest(request);
                        System.out.println("Request approved.");
                    } else if ("r".equals(decision)) {
                        manager.rejectLeaveRequest(request);
                        System.out.println("Request rejected.");
                    } else {
                        System.out.println("Invalid decision.");
                    }
                } catch (IllegalArgumentException | IllegalStateException exception) {
                    System.out.println("Could not process request: " + exception.getMessage());
                }
                return;
            }
        }
        System.out.println("Request not found or already decided.");
    }

    private static void viewEmployeeBalances(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee employee : employees) {
            System.out.println(employee.viewLeaveBalance());
        }
    }

    private static Employee findEmployee(String email) {
        for (Employee employee : employees) {
            if (employee.getEmail().equalsIgnoreCase(email.trim())) {
                return employee;
            }
        }
        return null;
    }

    private static String readLine(Scanner scanner, String fieldName) {
        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("No input was provided for " + fieldName + ".");
        }
        return scanner.nextLine();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            if (!scanner.hasNextLine()) {
                throw new IllegalStateException("No input was provided for this option.");
            }
            System.out.println("Please enter a whole number.");
            scanner.nextLine();
        }
    }
}
