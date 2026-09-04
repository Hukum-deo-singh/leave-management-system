import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--welcome to my console based leave management system--");
        System.out.println("choose your role : Manager or Employee ");

        String role = sc.nextLine();

        switch (role.toLowerCase()) {
            case "manager":
                System.out.print("enter your name :");
                String managerName = sc.nextLine();

                System.out.print("enter your email : ");
                String managerEmail = sc.nextLine();

                System.out.print("enter your password : ");
                String managerPassword = sc.nextLine();

                if ("manager@gmail.com".equals(managerEmail) && "Admin123".equals(managerPassword)) {
                    System.out.print("welcome manager " + managerName + ", nice to see you again .");

                    while (true) {
                        System.out.println("\n-- choose what operation you want to do --");
                        System.out.println("1. view the employees detail .");
                        System.out.println("2. view leave requests and approve/reject .");
                        System.out.println("3. view the employees balance .");
                        System.out.println("4. stop and go to home .");

                        int options = sc.nextInt();
                        sc.nextLine();

                        if (options == 4) {
                            System.out.println("thanks we will soon ");
                            break;
                        }

                        switch (options) {
                            case 1:
                                viewEmployeesDetail();
                                break;
                            case 2:
                                viewLeaveRequest();
                                break;
                            case 3:
                                viewEmployeesBalance();
                                break;
                            default:
                                System.out.println(" Please choose the right option ");
                                break;
                        }
                    }
                } else {
                    System.out.println("please enter the corrected email or password");
                }
                break;

            case "employee":
                System.out.print("enter your name :");
                String employeeName = sc.nextLine();

                System.out.print("enter your email : ");
                String employeeEmail = sc.nextLine();

                System.out.print("enter your password : ");
                String employeePassword = sc.nextLine();

                if ("employee@gmail.com".equals(employeeEmail) && "Employee123".equals(employeePassword)) {
                    System.out.print("welcome employee " + employeeName + ", nice to see you again .");

                    while (true) {
                        System.out.println("\n-- choose what operation you want to do --");
                        System.out.println("1. view leave requests approve/reject .");
                        System.out.println("2. view my balance .");
                        System.out.println("3. stop and go to home .");

                        int options = sc.nextInt();
                        sc.nextLine();

                        if (options == 3) {
                            System.out.println("thanks we will soon ");
                            break;
                        }

                        switch (options) {
                            case 1:
                                viewLeaveRequest();
                                break;
                            case 2:
                                viewLeaveBalance();
                                break;
                            default:
                                System.out.println(" Please choose the right option ");
                                break;
                        }
                    }
                } else {
                    System.out.println("please enter the corrected email or password");
                }
                break;

            default:
                System.out.print(" please type exactly like this Manager or Employee ");
                break;
        }
    }

    private static void viewEmployeesDetail() {
        System.out.println("Viewing employees details...");
    }

    private static void viewLeaveRequest() {
        System.out.println("Viewing leave requests...");
    }

    private static void viewEmployeesBalance() {
        System.out.println("Viewing employees balance...");
    }

    private static void viewLeaveBalance() {
        System.out.println("Viewing employee leave balance...");
    }
}