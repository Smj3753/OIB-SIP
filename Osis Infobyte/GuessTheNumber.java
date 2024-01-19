import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int numberOfRounds = 3;
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number!");
        System.out.println("You have " + numberOfRounds + " rounds. Let's get started!");

        for (int round = 1; round <= numberOfRounds; round++) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            int maxAttempts = 7; // You can adjust this limit

            System.out.println("\nRound " + round + ": Guess the number between " + lowerBound + " and " + upperBound);

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    int roundScore = maxAttempts - attempts + 1;
                    totalScore += roundScore;
                    System.out.println("Round Score: " + roundScore + " | Total Score: " + totalScore);
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + targetNumber);
            }

            if (round < numberOfRounds) {
                System.out.println("Next round!");
            }
        }

        System.out.println("\nGame Over! Your total score is: " + totalScore);
        scanner.close();
    }
}
