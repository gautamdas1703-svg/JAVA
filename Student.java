public class Student extends Person {
    private int rollNo;
    private String course;
    private double marks;
    private double attendance;

    public Student(int rollNo, String name, int age, String course, double marks, double attendance) {
        super(name, age);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        this.attendance = attendance;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public String calculateGrade() {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 50) return "D";
        else return "Fail";
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo +
                ", Name: " + name +
                ", Age: " + age +
                ", Course: " + course +
                ", Marks: " + marks +
                ", Attendance: " + attendance + "%" +
                ", Grade: " + calculateGrade();
    }

    public String toFileString() {
        return rollNo + "," + name + "," + age + "," + course + "," + marks + "," + attendance;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            return null;
        }

        int rollNo = Integer.parseInt(parts[0]);
        String name = parts[1];
        int age = Integer.parseInt(parts[2]);
        String course = parts[3];
        double marks = Double.parseDouble(parts[4]);
        double attendance = Double.parseDouble(parts[5]);

        return new Student(rollNo, name, age, course, marks, attendance);
    }
}