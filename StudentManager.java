import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (findStudentByRoll(student.getRollNo()) != null) {
            throw new IllegalArgumentException("Student with this roll number already exists.");
        }
        students.add(student);
    }

    public Student findStudentByRoll(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                return s;
            }
        }
        return null;
    }

    public boolean updateStudent(int rollNo, double marks, double attendance) {
        Student s = findStudentByRoll(rollNo);
        if (s != null) {
            s.setMarks(marks);
            s.setAttendance(attendance);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int rollNo) {
        Student s = findStudentByRoll(rollNo);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }
}