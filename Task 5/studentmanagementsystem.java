import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class studentmanagementsystem { // Class name changed here
    private List<Student> students;
    private Scanner scanner;
    private final String DATA_FILE = "students.txt";

    public studentmanagementsystem() { // Constructor name also changed
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadStudentsFromFile();
    }

    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = Student.fromFileString(line);
                if (student != null) students.add(student);
            }
            System.out.println("Student data loaded from " + DATA_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("No data file found. Creating new one.");
        } catch (IOException e) {
            System.err.println("Error reading data: " + e.getMessage());
        }
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }
            System.out.println("Student data saved to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter student name: ");
            name = scanner.nextLine().trim();
        }

        int rollNumber = -1;
        boolean rollNumberValid;
        do {
            rollNumberValid = true;
            System.out.print("Enter student roll number: ");
            try {
                rollNumber = scanner.nextInt();
                scanner.nextLine();

                if (rollNumber <= 0) {
                    System.out.println("Roll number must be positive.");
                    rollNumberValid = false;
                } else {
                    for (Student s : students) {
                        if (s.getRollNumber() == rollNumber) {
                            System.out.println("Roll number already exists. Enter a unique one.");
                            rollNumberValid = false;
                            break;
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a whole number.");
                scanner.nextLine();
                rollNumberValid = false;
            }
        } while (!rollNumberValid);

        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine().trim();
        while (grade.isEmpty()) {
            System.out.print("Grade cannot be empty. Enter student grade: ");
            grade = scanner.nextLine().trim();
        }

        students.add(new Student(name, rollNumber, grade));
        saveStudentsToFile();
        System.out.println("Student added successfully!");
    }

    public void removeStudent() {
        System.out.print("Enter roll number to remove: ");
        int rollNumberToRemove;
        try {
            rollNumberToRemove = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Enter a whole number.");
            scanner.nextLine();
            return;
        }

        boolean removed = students.removeIf(s -> s.getRollNumber() == rollNumberToRemove);
        if (removed) {
            saveStudentsToFile();
            System.out.println("Student " + rollNumberToRemove + " removed.");
        } else {
            System.out.println("Student " + rollNumberToRemove + " not found.");
        }
    }

    public void searchStudent() {
        System.out.print("Enter roll number to search: ");
        int rollNumberToSearch;
        try {
            rollNumberToSearch = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Enter a whole number.");
            scanner.nextLine();
            return;
        }

        Student foundStudent = null;
        for (Student student : students) {
            if (student.getRollNumber() == rollNumberToSearch) {
                foundStudent = student;
                break;
            }
        }
        if (foundStudent != null) {
            System.out.println("Student Found: " + foundStudent);
        } else {
            System.out.println("Student " + rollNumberToSearch + " not found.");
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        System.out.println("\n--- All Students ---");
        students.forEach(System.out::println);
        System.out.println("--------------------");
    }

    public void editStudent() {
        System.out.print("Enter roll number to edit: ");
        int rollNumberToEdit;
        try {
            rollNumberToEdit = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Enter a whole number.");
            scanner.nextLine();
            return;
        }

        Student studentToEdit = null;
        for (Student student : students) {
            if (student.getRollNumber() == rollNumberToEdit) {
                studentToEdit = student;
                break;
            }
        }

        if (studentToEdit == null) {
            System.out.println("Student " + rollNumberToEdit + " not found.");
            return;
        }

        System.out.println("Editing: " + studentToEdit);
        System.out.println("1. Name\n2. Grade");
        System.out.print("Enter choice: ");

        int editChoice;
        try {
            editChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Enter a number.");
            scanner.nextLine();
            return;
        }

        switch (editChoice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine().trim();
                while (newName.isEmpty()) {
                    System.out.print("Name cannot be empty. Enter new name: ");
                    newName = scanner.nextLine().trim();
                }
                studentToEdit.setName(newName);
                System.out.println("Name updated!");
                break;
            case 2:
                System.out.print("Enter new grade: ");
                String newGrade = scanner.nextLine().trim();
                while (newGrade.isEmpty()) {
                    System.out.print("Grade cannot be empty. Enter new grade: ");
                    newGrade = scanner.nextLine().trim();
                }
                studentToEdit.setGrade(newGrade);
                System.out.println("Grade updated!");
                break;
            default:
                System.out.println("Invalid edit choice.");
                return;
        }
        saveStudentsToFile();
    }

    public void displayMenu() {
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Add Student\n2. Remove Student\n3. Search Student\n4. Display All\n5. Edit Student\n6. Exit");
        System.out.print("Enter your choice: ");
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: removeStudent(); break;
                    case 3: searchStudent(); break;
                    case 4: displayAllStudents(); break;
                    case 5: editStudent(); break;
                    case 6: System.out.println("Exiting. Goodbye!"); break;
                    default: System.out.println("Invalid choice. Enter 1-6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != 6);
        scanner.close();
    }

    public static void main(String[] args) {
        new studentmanagementsystem().run(); // Updated here as well
    }
}
