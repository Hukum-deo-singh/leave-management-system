import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee extends Employees {
    private static final int DEFAULT_LEAVE_ALLOWANCE = 20;
    private final List<LeaveRequest> leaveRequests = new ArrayList<>();

    public Employee() {
        super();
    }

    public Employee(String employeeId, String fullName, String email) {
        super(employeeId, fullName, email, Role.EMPLOYEE, DEFAULT_LEAVE_ALLOWANCE);
    }

    public Employee(String employeeId, String fullName, String email, int annualLeaveAllowance) {
        super(employeeId, fullName, email, Role.EMPLOYEE, annualLeaveAllowance);
    }

    public LeaveRequest submitLeaveRequest(int requestId, int days, String reason) {
        LeaveRequest request = new LeaveRequest(requestId, this, days, reason);
        leaveRequests.add(request);
        return request;
    }

    public List<LeaveRequest> getLeaveRequests() {
        return Collections.unmodifiableList(leaveRequests);
    }

    public String viewLeaveBalance() {
        return getFullName() + " has " + getRemainingLeaveDays() +
                " of " + getAnnualLeaveAllowance() + " leave days remaining.";
    }
}
