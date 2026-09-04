# Leave Management System

A small console-based Java application for submitting, reviewing, and tracking employee leave requests.

## Features

- Employee login and leave request submission.
- Employee request history and remaining leave balance.
- Manager view of employees and leave requests.
- Manager approval or rejection of pending requests.
- Automatic balance update after an approval.
- Input validation for names, email addresses, leave days, and request reasons.
- Role selection and return navigation from the menus.

## Requirements

- Java Development Kit (JDK) 8 or newer.
- A terminal opened in the project directory.

## Run The Application

Compile all source files:

```powershell
javac *.java
```

Start the application:

```powershell
java Main
```

To remove compiled class files and rebuild:

```powershell
Remove-Item *.class -ErrorAction SilentlyContinue
javac *.java
```

## Login Details

The current demo credentials are stored in `Main.java`:

| Role | Email | Password |
| --- | --- | --- |
| Manager | `manager@gmail.com` | `Admin123` |
| Employee | `employee@gmail.com` | `Employee123` |

These credentials are for demonstration only. Do not use hardcoded passwords in a production system.

## How To Use

### Employee

1. Choose `Employee` at the role-selection screen.
2. Enter the employee credentials.
3. Choose `Submit leave request`.
4. Enter the number of days and a reason.
5. Use `View my leave requests` to check the request status.
6. Use `View my leave balance` to check available days.
7. Choose `4` to return to role selection.

### Manager

1. Choose `Manager` at the role-selection screen.
2. Enter the manager credentials.
3. Choose `View and decide leave requests`.
4. Enter a pending request ID, or enter `0` to cancel.
5. Enter `a` to approve or `r` to reject the request.
6. Use `View employee balances` to see updated balances.
7. Choose `4` to return to role selection.

## Project Structure

- `Main.java`: application entry point and console menus.
- `Employees.java`: validated parent class for shared employee data and leave balance behavior.
- `Employee.java`: employee-specific request and balance operations.
- `Manager.java`: manager-specific review, approval, rejection, and reporting operations.
- `LeaveRequest.java`: request details and `PENDING`, `APPROVED`, or `REJECTED` status.
- `Role.java`: available employee roles.

## Important Notes

- Data is stored only in memory and is lost when the program exits.
- A manager can review employee requests during the same application session.
- A leave request is pending until a manager approves or rejects it.
- Approved leave reduces the employee balance; rejected or pending leave does not.
