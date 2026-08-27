import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("---- Welcome to the Leave Management System ----");
        System.out.println("Let's start managing leaves, whether they are students, employees, or workers!");

        System.out.println("Enter your company/institution name: ");
        String companyName = sc.nextLine();

        System.out.println("Enter the no. of employees in your company/institution: ");
        int employeesCount = sc.nextInt();

        sc.nextLine(); // Consume leftover newline

        System.out.println("How do you want to manage the leave ('7 days' or '1 month')?");
        String manageLeave = sc.nextLine();

        sc.close();
    }
}