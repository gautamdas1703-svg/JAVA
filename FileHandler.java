import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static void saveStudentsToFile(String fileName, ArrayList<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                bw.write(s.toFileString());
                bw.newLine();
            }
            System.out.println("Student data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving file: " + e.getMessage());
        }
    }

    public static ArrayList<Student> loadStudentsFromFile(String fileName) {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromFileString(line);
                if (s != null) {
                    students.add(s);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while loading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid data format in file.");
        }

        return students;
    }
}