import java.util.Scanner;

public class FactorialRecursion {

    // Recursive method to compute factorial
    public static long factorial(int n) {
        if (n == 0) {
            return 1; // Base case: 0! = 1
        }
        return n * factorial(n - 1); // Recursive call
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Factorial Calculator (Using Recursion) ===");

        while (true) {
            System.out.print("Enter a non-negative integer (or type 'exit'): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            try {
                int n = Integer.parseInt(input);

                if (n < 0) {
                    System.out.println("Error: Factorial of negative numbers is undefined.");
                    continue;
                }

                long result = factorial(n);
                System.out.println("Factorial of " + n + " is: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid non-negative integer.");
            }
        }

        sc.close();
    }
}

// for compilation and execution
//javac FactorialRecursion.java
//java FactorialRecursion