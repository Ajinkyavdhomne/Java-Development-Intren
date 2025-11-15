import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class Employee {
    private final String id;
    private String name;
    private String department;
    private double salary;

    public Employee(String id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Dept: %s | Salary: %.2f", id, name, department, salary);
    }
}

class EmployeeManager {
    private final List<Employee> employees = new ArrayList<>();

    public void seedSampleData() {
        employees.add(new Employee("E001", "Amit Kumar", "Engineering", 50000));
        employees.add(new Employee("E002", "Sneha Patel", "Marketing", 42000));
        employees.add(new Employee("E003", "Ravi Sharma", "HR", 40000));
    }

    public boolean addEmployee(Employee e) {
        if (findById(e.getId()).isPresent()) {
            return false; // id must be unique
        }
        employees.add(e);
        return true;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees); // return copy to prevent external mutation
    }

    public Optional<Employee> findById(String id) {
        return employees.stream().filter(emp -> emp.getId().equalsIgnoreCase(id)).findFirst();
    }

    public boolean updateEmployee(String id, String name, String dept, Double salary) {
        Optional<Employee> opt = findById(id);
        if (!opt.isPresent()) return false;
        Employee e = opt.get();
        if (name != null && !name.trim().isEmpty()) e.setName(name);
        if (dept != null && !dept.trim().isEmpty()) e.setDepartment(dept);
        if (salary != null) e.setSalary(salary);
        return true;
    }

    public boolean deleteEmployee(String id) {
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equalsIgnoreCase(id)) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}

public class EmployeeManagementSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final EmployeeManager manager = new EmployeeManager();

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric value (e.g. 42000).");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Employee Management ===");
        System.out.println("1. List all employees");
        System.out.println("2. Add employee");
        System.out.println("3. Update employee");
        System.out.println("4. Delete employee");
        System.out.println("5. View single employee");
        System.out.println("6. Exit");
    }

    public static void main(String[] args) {
        manager.seedSampleData();
        System.out.println("Employee Management System (in-memory)");

        while (true) {
            printMenu();
            String choice = readLine("Choose (1-6): ");

            switch (choice) {
                case "1":
                    System.out.println("\n--- All Employees ---");
                    manager.getAll().forEach(System.out::println);
                    break;
                case "2":
                    System.out.println("\n--- Add Employee ---");
                    String id = readLine("Enter ID (e.g. E010): ");
                    String name = readLine("Enter name: ");
                    String dept = readLine("Enter department: ");
                    double salary = readDouble("Enter salary: ");
                    boolean added = manager.addEmployee(new Employee(id, name, dept, salary));
                    System.out.println(added ? "Employee added." : "Employee with this ID already exists.");
                    break;
                case "3":
                    System.out.println("\n--- Update Employee ---");
                    String uid = readLine("Enter employee ID to update: ");
                    if (!manager.findById(uid).isPresent()) {
                        System.out.println("Employee not found.");
                        break;
                    }
                    String newName = readLine("New name (leave blank to keep current): ");
                    String newDept = readLine("New department (leave blank to keep current): ");
                    String salaryInput = readLine("New salary (leave blank to keep current): ");
                    Double newSalary = null;
                    if (!salaryInput.isEmpty()) {
                        try {
                            newSalary = Double.parseDouble(salaryInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid salary - update aborted.");
                            break;
                        }
                    }
                    boolean updated = manager.updateEmployee(uid, newName, newDept, newSalary);
                    System.out.println(updated ? "Employee updated." : "Update failed.");
                    break;
                case "4":
                    System.out.println("\n--- Delete Employee ---");
                    String did = readLine("Enter employee ID to delete: ");
                    boolean deleted = manager.deleteEmployee(did);
                    System.out.println(deleted ? "Employee deleted." : "Employee not found.");
                    break;
                case "5":
                    System.out.println("\n--- View Employee ---");
                    String vid = readLine("Enter employee ID to view: ");
                    manager.findById(vid).ifPresentOrElse(
                        emp -> System.out.println(emp),
                        () -> System.out.println("Employee not found.")
                    );
                    break;
                case "6":
                    System.out.println("Exiting. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Choose 1-6.");
            }
        }
    }
}

// for compilation and execution

//javac EmployeeManagementSystem.java
//java EmployeeManagementSystem