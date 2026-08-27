import java.util.Scanner

class Main {
public static void main (String[] args)
{
Scanner sc = new Scanner();
System.out.println("----welcome to the leave management system ----");
System.out.println("let's start managing leaves don't matter they are students, employees and worker !!");
System.out.println("Enter your company/institution name: ");
String companyName = sc.nextLine();
System.out.println("Enter the no. of employees in your company/institution: ");
int employeesCount = sc.nextInt();
System.out.println("How you want to manage the leave ('7 days or 1 month')");
manageLeave = sc.nextLine();
}
}