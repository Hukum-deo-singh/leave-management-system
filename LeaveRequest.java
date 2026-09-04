public final class LeaveRequest {
    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    private final int requestId;
    private final Employee employee;
    private final int days;
    private final String reason;
    private Status status;

    LeaveRequest(int requestId, Employee employee, int days, String reason) {
        if (requestId <= 0) {
            throw new IllegalArgumentException("Request ID must be positive");
        }
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Leave days must be greater than zero");
        }
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Leave reason cannot be blank");
        }
        this.requestId = requestId;
        this.employee = employee;
        this.days = days;
        this.reason = reason.trim();
        this.status = Status.PENDING;
    }

    public int getRequestId() {
        return requestId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getDays() {
        return days;
    }

    public String getReason() {
        return reason;
    }

    public Status getStatus() {
        return status;
    }

    void approve() {
        if (status != Status.PENDING) {
            throw new IllegalStateException("Only pending requests can be approved");
        }
        employee.requestLeave(days);
        status = Status.APPROVED;
    }

    void reject() {
        if (status != Status.PENDING) {
            throw new IllegalStateException("Only pending requests can be rejected");
        }
        status = Status.REJECTED;
    }

    @Override
    public String toString() {
        return "Request #" + requestId + " | " + employee.getFullName() +
                " | days: " + days + " | reason: " + reason + " | status: " + status;
    }
}
