import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {
    static final String DB_URL = "jdbc:mysql://localhost:3306/companydb";
    static final String USER = "root";
    static final String PASS = ""; // or your MySQL password

    static Connection conn = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database.");

            int choice;
            do {
                System.out.println("\n--- Employee Database Menu ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> viewEmployees();
                    case 3 -> updateEmployee();
                    case 4 -> deleteEmployee();
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice.");
                }

            } while (choice != 5);

        } catch (SQLException e) {
            System.out.println("DB connection error: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                scanner.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection.");
            }
        }
    }

    static void addEmployee() throws SQLException {
        System.out.print("Enter name: ");
        scanner.nextLine(); // consume newline
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String dept = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, dept);
        pstmt.setDouble(3, salary);

        int rows = pstmt.executeUpdate();
        System.out.println(rows > 0 ? "Employee added successfully." : "Failed to add employee.");
    }

    static void viewEmployees() throws SQLException {
        String sql = "SELECT * FROM employees";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nEmployee Records:");
        while (rs.next()) {
            System.out.printf("ID: %d | Name: %s | Dept: %s | Salary: %.2f\n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("salary"));
        }
    }

    static void updateEmployee() throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new department: ");
        String dept = scanner.nextLine();
        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();

        String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, dept);
        pstmt.setDouble(3, salary);
        pstmt.setInt(4, id);

        int rows = pstmt.executeUpdate();
        System.out.println(rows > 0 ? "Employee updated successfully." : "Employee not found.");
    }

    static void deleteEmployee() throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM employees WHERE id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        int rows = pstmt.executeUpdate();
        System.out.println(rows > 0 ? "Employee deleted successfully." : "Employee not found.");
    }
}
