import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    int imarks;

    Student(int id, String name, int imarks) {
        this.id = id;
        this.name = name;
        this.imarks = imarks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Internal Marks: " + imarks;
    }
}

public class StudentManager {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    static void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Internal Marks: ");
        int imarks = scanner.nextInt();

        students.add(new Student(id, name, imarks));
        System.out.println("Student added successfully.");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("\nList of Students:");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        boolean found = false;

        for (Student s : students) {
            if (s.id == id) {
                scanner.nextLine(); // consume newline
                System.out.print("Enter new name: ");
                s.name = scanner.nextLine();
                System.out.print("Enter new internal marks: ");
                s.imarks = scanner.nextInt();
                found = true;
                System.out.println("Student updated.");
                break;
            }
        }

        if (!found) {
            System.out.println("Student ID not found.");
        }
    }

    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        boolean removed = students.removeIf(s -> s.id == id);

        if (removed) {
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student ID not found.");
        }
    }
}
