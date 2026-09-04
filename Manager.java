import java.util.List;

public class Manager extends Employees {
    private static final int DEFAULT_LEAVE_ALLOWANCE = 25;

    public Manager() {
        super("MANAGER", "Manager", "manager@example.com", Role.MANAGER, DEFAULT_LEAVE_ALLOWANCE);
    }

    public Manager(String employeeId, String fullName, String email) {
        super(employeeId, fullName, email, Role.MANAGER, DEFAULT_LEAVE_ALLOWANCE);
    }

    public Manager(String employeeId, String fullName, String email, int annualLeaveAllowance) {
        super(employeeId, fullName, email, Role.MANAGER, annualLeaveAllowance);
    }

    public void approveLeaveRequest(LeaveRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        request.approve();
    }

    public void rejectLeaveRequest(LeaveRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        request.reject();
    }

    public void viewEmployees(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public void viewLeaveRequests(List<LeaveRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            System.out.println("No leave requests found.");
            return;
        }
        for (LeaveRequest request : requests) {
            System.out.println(request);
        }
    }
}
