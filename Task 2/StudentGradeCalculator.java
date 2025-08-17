import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! Let's calculate your academic performance.");

        System.out.print("How many subjects do you have? ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a whole number for subjects.");
            scanner.next();
            System.out.print("How many subjects do you have? ");
        }
        int numberOfSubjects = scanner.nextInt();

        if (numberOfSubjects <= 0) {
            System.out.println("You must have at least one subject to calculate grades. Exiting.");
            scanner.close();
            return;
        }

        int totalMarks = 0;

        System.out.println("\nPlease enter marks (out of 100) for each subject:");
        for (int i = 1; i <= numberOfSubjects; i++) {
            System.out.print("Marks for Subject " + i + ": ");
            int subjectMarks;
            while (true) {
                if (scanner.hasNextInt()) {
                    subjectMarks = scanner.nextInt();
                    if (subjectMarks >= 0 && subjectMarks <= 100) {
                        totalMarks += subjectMarks;
                        break;
                    } else {
                        System.out.println("Marks must be between 0 and 100. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a whole number.");
                    scanner.next();
                }
                System.out.print("Marks for Subject " + i + ": ");
            }
        }

        double averagePercentage = (double) totalMarks / numberOfSubjects;

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        }
        else {
            grade = 'F';
        }

        System.out.println("\n--- Your Results ---");
        System.out.println("Total Subjects: " + numberOfSubjects);
        System.out.println("Total Marks Obtained: " + totalMarks + " / " + (numberOfSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Your Grade: " + grade);

        System.out.println("\nThank you for using the Student Grade Calculator!");

        scanner.close();
    }
}
