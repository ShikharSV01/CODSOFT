import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = true;
        int totalRoundsWon = 0;

        System.out.println("Hello! Welcome to the Number Game. Guess a number between 1 and 100.");

        while (playAgain) {
            System.out.print("\nPlease enter your name: ");
            String playerName = scanner.next();

            int targetNumber = random.nextInt(100) + 1;
            int maxAttempts = 10;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nOkay, " + playerName + "! I've thought of a new number. You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess (" + (attempts + 1) + "/" + maxAttempts + "): ");
                
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input, " + playerName + ". Please enter a whole number.");
                    scanner.next();
                    System.out.print("Enter your guess (" + (attempts + 1) + "/" + maxAttempts + "): ");
                }
                
                int playerGuess = scanner.nextInt();
                attempts++;

                if (playerGuess == targetNumber) {
                    System.out.println("🎉 Congratulations, " + playerName + "! You guessed " + targetNumber + " in " + attempts + " attempts!");
                    guessedCorrectly = true;
                    totalRoundsWon++;
                } else if (playerGuess < targetNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("😞 Out of attempts, " + playerName + "! The number was " + targetNumber + ".");
            }

            System.out.println("\nTotal rounds won: " + totalRoundsWon);
            System.out.print("Do you want to play again, " + playerName + "? (yes/no): ");
            String playerChoice = scanner.next().toLowerCase();

            if (!playerChoice.equals("yes")) {
                playAgain = false;
                System.out.println("Thanks for playing, " + playerName + "! Goodbye.");
            }
        }

        scanner.close();
    }
}
