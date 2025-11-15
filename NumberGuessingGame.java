import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("=== Number Guessing Game ===");

        boolean play = true;
        while (play) {
            int min = 1;
            int max = 100;
            int maxAttempts = 7;

            int secret = rand.nextInt(max - min + 1) + min;
            System.out.printf("I have chosen a number between %d and %d. You have %d attempts.%n", min, max, maxAttempts);

            int attempts = 0;
            boolean won = false;
            while (attempts < maxAttempts) {
                int guess = readInt(sc, "Enter your guess: ");
                attempts++;
                if (guess == secret) {
                    System.out.printf("Correct! You guessed it in %d attempt(s).%n", attempts);
                    won = true;
                    break;
                } else if (guess < secret) {
                    System.out.println("Too low.");
                } else {
                    System.out.println("Too high.");
                }
                System.out.printf("Attempts left: %d%n", maxAttempts - attempts);
            }

            if (!won) {
                System.out.printf("Out of attempts — the number was %d.%n", secret);
            }

            System.out.print("Play again? (y/n): ");
            String ans = sc.nextLine().trim().toLowerCase();
            play = ans.equals("y") || ans.equals("yes");
        }

        System.out.println("Thanks for playing!");
        sc.close();
    }
}

// for compilation and execution
//javac NumberGuessingGame.java
//java NumberGuessingGame
