public class Employee {
    private String name;
    private String id;
    private String dept;
    private double basic_salary;
    private double extra_salary;
    private double income;

    Employee(
        String id,
        String name,
        String dept,
        double basic_salary,
        double extra_salary
    ) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.basic_salary = basic_salary;
        this.extra_salary = extra_salary;
        this.income = basic_salary + extra_salary * 2.5;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public double getBasicSalary() {
        return basic_salary;
    }

    public double getExtraSalary() {
        return extra_salary;
    }

    public double getIncome() {
        return income;
    }

    // Convert to strings to display in the .txt file
    public String display() {
        String printID = "ID: " + this.id;
        String printName = "Name: " + this.name;
        String printDept = "Department: " + this.dept;
        String printIncome = "Income: " + this.income;

        return printID + "\r\n" + printName + "\r\n" + printDept + "\r\n" + printIncome + "\r\n";
    }

    // public void writeFile(String file_name, Employee[] employees) {
    //     try {
    //         FileWriter fw = new FileWriter("employees.txt");
    //         for (Employee emp: employees) {
    //             fw.write(this.display() + "\r\n");
    //         }
    //         fw.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}


