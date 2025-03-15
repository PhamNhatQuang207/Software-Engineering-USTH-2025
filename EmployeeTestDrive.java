import java.io.*;
import java.util.Scanner;

public class EmployeeTestDrive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of employees: ");
        int num = sc.nextInt();

        Employee[] employees = new Employee[num];
        
        for (int i = 0; i < num; i++) {
            System.out.println("Employee " + (i+1) + ":");
            System.out.println("ID: ");
            String id = sc.next();
            System.out.println("Name: ");
            String name = sc.next();
            System.out.println("Department: ");
            String dept = sc.next();
            System.out.println("Basic salary: ");
            double basic_salary = sc.nextDouble();
            System.out.println("Extra salary: ");
            double extra_salary = sc.nextDouble();
            
            employees[i] = new Employee(id, name, dept, basic_salary, extra_salary);
        }
        writeFile("employees.txt", employees);
        showEmployees();
        sc.close();
    }
    
    public static void writeFile(String file_name, Employee[] employees) {
        try {
            FileWriter fw = new FileWriter("employees.txt");
            for (Employee emp: employees) {
                fw.write(emp.display() + "\r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public static void showEmployees() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("employees.txt"));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
