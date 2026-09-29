import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class Student {

    private int id;
    private String name;
    private int marks;

    // Constructor
    Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    // Calculate grade
    public String getGrade() {
        if (marks >= 90)
            return "A+";
        else if (marks >= 80)
            return "A";
        else if (marks >= 70)
            return "B";
        else if (marks >= 60)
            return "C";
        else if (marks >= 50)
            return "D";
        else
            return "F";
    }

    // Display student
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + getGrade());
        System.out.println("----------------------");
    }
}

public class StudentManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    // Add student
    static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = getValidInteger();

        // Check duplicate ID
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("Student ID already exists!");
                return;
            }
        }

        String name = getValidName();
        int marks = getValidMarks();

        Student s = new Student(id, name, marks);
        students.add(s);

        System.out.println("Student added successfully!");
    }

    // View all students
    static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n===== All Students =====");

        for (Student s : students) {
            s.display();
        }
    }

    // Search by ID
    static void searchStudent() {

        System.out.print("Enter Student ID: ");
        int id = getValidInteger();

        for (Student s : students) {

            if (s.getId() == id) {
                System.out.println("\nStudent found!");
                s.display();
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Search by name
    static void searchByName() {

        String name = getValidName();

        boolean found = false;

        for (Student s : students) {

            if (s.getName().equalsIgnoreCase(name)) {
                s.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Update student
    static void updateStudent() {

        System.out.print("Enter Student ID to update: ");
        int id = getValidInteger();

        for (Student s : students) {

            if (s.getId() == id) {

                System.out.println("Enter new details:");

                String name = getValidName();
                int marks = getValidMarks();

                s.setName(name);
                s.setMarks(marks);

                System.out.println("Student updated successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Delete student
    static void deleteStudent() {

        System.out.print("Enter Student ID to delete: ");
        int id = getValidInteger();

        for (int i = 0; i < students.size(); i++) {

            Student s = students.get(i);

            if (s.getId() == id) {

                students.remove(i);

                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Display marks
    static void displayMarks() {

        System.out.print("Enter Student ID: ");
        int id = getValidInteger();

        for (Student s : students) {

            if (s.getId() == id) {

                System.out.println("Student Name: " + s.getName());
                System.out.println("Marks: " + s.getMarks());
                System.out.println("Grade: " + s.getGrade());

                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Class statistics
    static void showStatistics() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        int total = 0;
        int highest = students.get(0).getMarks();
        int lowest = students.get(0).getMarks();

        for (Student s : students) {

            int marks = s.getMarks();

            total += marks;

            if (marks > highest) {
                highest = marks;
            }

            if (marks < lowest) {
                lowest = marks;
            }
        }

        double average = (double) total / students.size();

        System.out.println("\n===== Class Statistics =====");
        System.out.println("Total Students: " + students.size());
        System.out.println("Average Marks: " + average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);
    }

    // Sort students by marks
    static void sortByMarks() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (int i = 0; i < students.size() - 1; i++) {

            for (int j = 0; j < students.size() - i - 1; j++) {

                if (students.get(j).getMarks()
                        < students.get(j + 1).getMarks()) {

                    Student temp = students.get(j);

                    students.set(j, students.get(j + 1));

                    students.set(j + 1, temp);
                }
            }
        }

        System.out.println("Students sorted by marks!");
        viewStudents();
    }

    // Validate integer
    static int getValidInteger() {

        while (!sc.hasNextInt()) {

            System.out.println("Invalid input! Please enter a number.");

            sc.next();

            System.out.print("Enter again: ");
        }

        return sc.nextInt();
    }

    // Validate marks
    static int getValidMarks() {

        while (true) {

            System.out.print("Enter marks (0-100): ");

            if (sc.hasNextInt()) {

                int marks = sc.nextInt();

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println(
                    "Invalid marks! Please enter between 0 and 100."
                );

            } else {

                System.out.println(
                    "Invalid input! Please enter a number."
                );

                sc.next();
            }
        }
    }

    // Validate name
    static String getValidName() {

        sc.nextLine();

        while (true) {

            System.out.print("Enter Student Name: ");

            String name = sc.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println("Name cannot be empty.");
        }
    }

    // Save data
    static void saveData() {

        try {

            FileWriter writer = new FileWriter("students.txt");

            for (Student s : students) {

                writer.write(
                    s.getId() + "|" +
                    s.getName() + "|" +
                    s.getMarks() + "\n"
                );
            }

            writer.close();

            System.out.println("Student data saved successfully!");

        } catch (IOException e) {

            System.out.println("Error while saving student data.");
        }
    }

    // Load data
    static void loadData() {

        try {

            File file = new File("students.txt");

            if (!file.exists()) {
                System.out.println("No previous student data found.");
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                try {

                    String[] parts = line.split("\\|");

                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int marks = Integer.parseInt(parts[2]);

                    Student s = new Student(id, name, marks);

                    students.add(s);

                } catch (Exception e) {

                    System.out.println("Invalid data found in file.");
                }
            }

            fileScanner.close();

            System.out.println("Student data loaded successfully!");

        } catch (FileNotFoundException e) {

            System.out.println("Error while reading student data.");
        }
    }

    // Main method
    public static void main(String[] args) {

        loadData();

        while (true) {

            System.out.println("\n================================");
            System.out.println("     STUDENT MANAGEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");
            System.out.println("7. Display Marks & Grade");
            System.out.println("8. Show Class Statistics");
            System.out.println("9. Sort Students by Marks");
            System.out.println("10. Save Data");
            System.out.println("11. Exit");

            System.out.print("Enter your choice: ");

            int choice = getValidInteger();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    searchByName();
                    break;

                case 5:
                    updateStudent();
                    break;

                case 6:
                    deleteStudent();
                    break;

                case 7:
                    displayMarks();
                    break;

                case 8:
                    showStatistics();
                    break;

                case 9:
                    sortByMarks();
                    break;

                case 10:
                    saveData();
                    break;

                case 11:
                    saveData();
                    System.out.println(
                        "Thank you for using Student Management System!"
                    );
                    sc.close();
                    return;

                default:
                    System.out.println(
                        "Invalid choice! Please choose between 1 and 11."
                    );
            }
        }
    }
}
