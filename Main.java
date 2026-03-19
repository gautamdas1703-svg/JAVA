import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        ArrayList<Student> loadedStudents = FileHandler.loadStudentsFromFile(FILE_NAME);
        manager.getStudents().addAll(loadedStudents);

        int choice = 0;

        do {
            System.out.println("\n===== SMART STUDENT MANAGER SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Roll Number");
            System.out.println("4. Update Student Marks and Attendance");
            System.out.println("5. Delete Student");
            System.out.println("6. Save Students to File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addStudent(sc, manager);
                        break;
                    case 2:
                        manager.displayAllStudents();
                        break;
                    case 3:
                        searchStudent(sc, manager);
                        break;
                    case 4:
                        updateStudent(sc, manager);
                        break;
                    case 5:
                        deleteStudent(sc, manager);
                        break;
                    case 6:
                        FileHandler.saveStudentsToFile(FILE_NAME, manager.getStudents());
                        break;
                    case 7:
                        FileHandler.saveStudentsToFile(FILE_NAME, manager.getStudents());
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter between 1 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter correct data type.");
                sc.nextLine();
                choice = 0;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 7);

        sc.close();
    }

    public static void addStudent(Scanner sc, StudentManager manager) {
        try {
            System.out.print("Enter Roll Number: ");
            int rollNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            System.out.print("Enter Attendance Percentage: ");
            double attendance = sc.nextDouble();

            if (marks < 0 || marks > 100 || attendance < 0 || attendance > 100) {
                System.out.println("Marks and attendance must be between 0 and 100.");
                return;
            }

            Student student = new Student(rollNo, name, age, course, marks, attendance);
            manager.addStudent(student);

            System.out.println("Student added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Wrong input type entered.");
            sc.nextLine();
        }
    }

    public static void searchStudent(Scanner sc, StudentManager manager) {
        try {
            System.out.print("Enter Roll Number to search: ");
            int rollNo = sc.nextInt();

            Student s = manager.findStudentByRoll(rollNo);
            if (s != null) {
                System.out.println("Student Found:");
                System.out.println(s);
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid roll number.");
            sc.nextLine();
        }
    }

    public static void updateStudent(Scanner sc, StudentManager manager) {
        try {
            System.out.print("Enter Roll Number to update: ");
            int rollNo = sc.nextInt();

            System.out.print("Enter new marks: ");
            double marks = sc.nextDouble();

            System.out.print("Enter new attendance: ");
            double attendance = sc.nextDouble();

            if (marks < 0 || marks > 100 || attendance < 0 || attendance > 100) {
                System.out.println("Marks and attendance must be between 0 and 100.");
                return;
            }

            boolean updated = manager.updateStudent(rollNo, marks, attendance);
            if (updated) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            sc.nextLine();
        }
    }

    public static void deleteStudent(Scanner sc, StudentManager manager) {
        try {
            System.out.print("Enter Roll Number to delete: ");
            int rollNo = sc.nextInt();

            boolean deleted = manager.deleteStudent(rollNo);
            if (deleted) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid roll number.");
            sc.nextLine();
        }
    }
}