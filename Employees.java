import java.util.Objects;

public class Employees {
	private final String employeeId;
	private String fullName;
	private String email;
	private final Role role;
	private final int annualLeaveAllowance;
	private int usedLeaveDays;

	protected Employees() {
		this("UNASSIGNED", "Unknown", "unknown@example.com", Role.EMPLOYEE, 20);
	}

	protected Employees(String employeeId, String fullName, String email, Role role,
			int annualLeaveAllowance) {
		this.employeeId = requireText(employeeId, "Employee ID");
		this.fullName = requireText(fullName, "Full name");
		this.email = requireEmail(email);
		this.role = Objects.requireNonNull(role, "Role cannot be null");
		if (annualLeaveAllowance < 0) {
			throw new IllegalArgumentException("Annual leave allowance cannot be negative");
		}
		this.annualLeaveAllowance = annualLeaveAllowance;
	}

	public final String getEmployeeId() {
		return employeeId;
	}

	public final String getFullName() {
		return fullName;
	}

	public final void setFullName(String fullName) {
		this.fullName = requireText(fullName, "Full name");
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = requireEmail(email);
	}

	public final Role getRole() {
		return role;
	}

	public final int getAnnualLeaveAllowance() {
		return annualLeaveAllowance;
	}

	public final int getUsedLeaveDays() {
		return usedLeaveDays;
	}

	public final int getRemainingLeaveDays() {
		return annualLeaveAllowance - usedLeaveDays;
	}

	public final void requestLeave(int days) {
		if (days <= 0) {
			throw new IllegalArgumentException("Leave days must be greater than zero");
		}
		if (days > getRemainingLeaveDays()) {
			throw new IllegalArgumentException("Insufficient leave balance");
		}
		usedLeaveDays += days;
	}

	public final void restoreLeave(int days) {
		if (days <= 0 || days > usedLeaveDays) {
			throw new IllegalArgumentException("Invalid number of leave days to restore");
		}
		usedLeaveDays -= days;
	}

	@Override
	public String toString() {
		return "Employees{" +
				"employeeId='" + employeeId + '\'' +
				", fullName='" + fullName + '\'' +
				", email='" + email + '\'' +
				", role=" + role +
				", remainingLeaveDays=" + getRemainingLeaveDays() +
				'}';
	}

	private static String requireText(String value, String fieldName) {
		String trimmedValue = Objects.requireNonNull(value, fieldName + " cannot be null").trim();
		if (trimmedValue.isEmpty()) {
			throw new IllegalArgumentException(fieldName + " cannot be blank");
		}
		return trimmedValue;
	}

	private static String requireEmail(String email) {
		String validEmail = requireText(email, "Email");
		if (!validEmail.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
			throw new IllegalArgumentException("Invalid email address");
		}
		return validEmail;
	}
}